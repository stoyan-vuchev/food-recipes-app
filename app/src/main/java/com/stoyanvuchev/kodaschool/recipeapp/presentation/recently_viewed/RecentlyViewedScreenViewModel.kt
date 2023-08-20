package com.stoyanvuchev.kodaschool.recipeapp.presentation.recently_viewed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.Result
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.UiString
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
class RecentlyViewedScreenViewModel @Inject constructor(
    private val repository: RecipesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RecentlyViewedScreenState())
    val state = _state.asStateFlow()

    private val _uiActionChannel = Channel<RecentlyViewedScreenUiAction>()
    val uiActionFlow = _uiActionChannel.receiveAsFlow()

    private var debounceJob: Job? = null

    init {
        viewModelScope.launch {
            delay(1000L)
            getRecentlyViewedRecipes(category = RecipesCategory.All)
        }
    }

    fun onUiAction(uiAction: RecentlyViewedScreenUiAction) {
        when (uiAction) {
            is RecentlyViewedScreenUiAction.GoBack -> sendUiAction(uiAction)
            is RecentlyViewedScreenUiAction.SetCategory -> setCategory(uiAction)
            is RecentlyViewedScreenUiAction.SetSaved -> saveRecipe(uiAction)
            is RecentlyViewedScreenUiAction.ViewRecipe -> sendUiAction(uiAction)
        }
    }

    private fun setCategory(uiAction: RecentlyViewedScreenUiAction.SetCategory) {
        _state.update { it.copy(category = uiAction.category, isLoading = true) }
        viewModelScope.launch {
            debounceJob?.cancel()
            debounceJob = this.launch {
                delay(500L)
                getRecentlyViewedRecipes(uiAction.category)
                debounceJob = null
            }
        }
    }

    private fun saveRecipe(uiAction: RecentlyViewedScreenUiAction.SetSaved) {

        _state.value.recipes.find { it.recipeId == uiAction.recipeId }?.let { recipe ->

            val timestamp = if (uiAction.isSaved) System.currentTimeMillis() else null
            val updatedRecipe = recipe.copy(
                isBookmarked = uiAction.isSaved,
                bookmarkTimestamp = timestamp
            )

            viewModelScope.launch(Dispatchers.IO) {
                repository.updateRecipe(updatedRecipe).onEach {}.launchIn(this)
            }

        }

    }

    private fun getRecentlyViewedRecipes(category: RecipesCategory) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = repository.getRecentRecipesByCategory(category)
                withContext(Dispatchers.Default) {
                    onGetRecentRecipesByCategoryResult(result)
                }
            }
        }
    }

    private fun onGetRecentRecipesByCategoryResult(result: Result<List<RecipeModel>>) {
        when (result) {

            is Result.Loading -> Unit

            is Result.Success -> _state.update {
                it.copy(
                    error = UiString.EmptyString,
                    recipes = result.data,
                    isLoading = false
                )
            }

            is Result.Error -> _state.update {
                it.copy(
                    error = result.error,
                    recipes = emptyList(),
                    isLoading = false
                )
            }

        }
    }

    private fun sendUiAction(uiAction: RecentlyViewedScreenUiAction) {
        viewModelScope.launch { _uiActionChannel.send(uiAction) }
    }

}