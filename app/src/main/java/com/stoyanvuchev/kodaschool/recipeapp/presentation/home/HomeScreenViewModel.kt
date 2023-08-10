package com.stoyanvuchev.kodaschool.recipeapp.presentation.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.Result
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel
import com.stoyanvuchev.kodaschool.recipeapp.domain.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: RecipesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private val _uiActionChannel = Channel<HomeScreenUiAction>()
    val uiActionFlow = _uiActionChannel.receiveAsFlow()

    private val _recentRecipes = mutableStateListOf<RecipeModel>()
    val recentRecipes: List<RecipeModel> = _recentRecipes

    private val _categorizedRecipes = mutableStateListOf<RecipeModel>()
    val categorizedRecipes = MutableStateFlow(_categorizedRecipes).asStateFlow()

    private var categoryLoadJob: Job? = null
    private var categoryDebounceJob: Job? = null

    init {
        viewModelScope.launch {
            delay(256L)
            getRecentRecipes()
            getRecipesByCategory(category = RecipesCategory.Default)
        }
    }

    override fun onCleared() {
        super.onCleared()
        categoryLoadJob?.cancel()
        categoryDebounceJob?.cancel()
    }

    fun onUiAction(uiAction: HomeScreenUiAction) {
        when (uiAction) {

            is HomeScreenUiAction.SetCategory -> setCategory(uiAction.category)
            is HomeScreenUiAction.ViewRecipe -> viewRecipe(uiAction)

            is HomeScreenUiAction.SaveOrRemoveRecipe -> setSavedRecipeState(
                recipeId = uiAction.recipeId,
                saved = uiAction.saved
            )

        }
    }

    private fun viewRecipe(uiAction: HomeScreenUiAction.ViewRecipe) {

        val recipe = (_recentRecipes + _categorizedRecipes)
            .find { it.recipeId == uiAction.recipeId }

        recipe?.let {
            sendUiAction(uiAction)
            val updatedRecipe = recipe.copy(lastViewedTimestamp = System.currentTimeMillis())
            viewModelScope.launch(Dispatchers.IO) {
                repository.updateRecipe(updatedRecipe).onEach { }.launchIn(this)
            }
        }

    }

    private fun getRecentRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRecentRecipes(20).onEach {
                _recentRecipes.clear()
                _recentRecipes.addAll(it)
            }.launchIn(this)
        }
    }

    private fun getRecipesByCategory(category: RecipesCategory) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryLoadJob?.cancel()
            categoryLoadJob = this.launch {
                val recipes = repository.getRecipesByCategory(category)
                onGetRecipesByCategoryResult(recipes)
            }
        }
    }

    private suspend fun onGetRecipesByCategoryResult(recipes: List<RecipeModel>) {
        withContext(Dispatchers.Default) {
            _categorizedRecipes.clear()
            _categorizedRecipes.addAll(recipes)
            _state.update { it.copy(isLoadingCategory = false) }
        }
    }

    private fun setSavedRecipeState(recipeId: String, saved: Boolean) {

        val recipe = (_recentRecipes + _categorizedRecipes)
            .find { it.recipeId == recipeId }

        recipe?.let {

            val timestamp = if (saved) System.currentTimeMillis() else null
            val updatedRecipe = recipe.copy(
                isBookmarked = saved,
                bookmarkTimestamp = timestamp
            )

            viewModelScope.launch(Dispatchers.IO) {
                repository.updateRecipe(updatedRecipe)
                    .onEach { result ->
                        onSetSavedRecipeState(
                            result = result,
                            recipeId = recipe.recipeId,
                            updatedRecipe = updatedRecipe
                        )
                    }.launchIn(this)
            }

        }

    }

    private fun onSetSavedRecipeState(
        result: Result<Unit>,
        recipeId: String,
        updatedRecipe: RecipeModel
    ) {
        when (result) {

            is Result.Success -> {
                val recipe = _categorizedRecipes.find { it.recipeId == recipeId }
                recipe?.let {
                    _categorizedRecipes.set(
                        index = _categorizedRecipes.indexOf(recipe),
                        element = updatedRecipe
                    )
                }
            }

            else -> Unit

        }
    }

    private fun setCategory(category: RecipesCategory) {
        _state.update { it.copy(isLoadingCategory = true, category = category) }
        viewModelScope.launch {
            categoryDebounceJob?.cancel()
            categoryDebounceJob = this.launch {
                delay(600L)
                getRecipesByCategory(category = category)
            }
        }
    }

    private fun sendUiAction(uiAction: HomeScreenUiAction) {
        viewModelScope.launch { _uiActionChannel.send(uiAction) }
    }

}