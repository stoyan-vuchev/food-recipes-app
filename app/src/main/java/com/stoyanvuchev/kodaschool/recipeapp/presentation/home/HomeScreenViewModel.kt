package com.stoyanvuchev.kodaschool.recipeapp.presentation.home

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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
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

    val recentRecipes = repository.getRecentRecipes(20)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _categorizedRecipes = MutableStateFlow<List<RecipeModel>>(emptyList())
    val categorizedRecipes = _categorizedRecipes.asStateFlow()

    private var categoryLoadJob: Job? = null
    private var categoryDebounceJob: Job? = null

    init {
        viewModelScope.launch {
            delay(256L)
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

            is HomeScreenUiAction.Search -> sendUiAction(uiAction)
            is HomeScreenUiAction.SeeAllRecentlyViewed -> sendUiAction(uiAction)
            is HomeScreenUiAction.SetCategory -> setCategory(uiAction.category)
            is HomeScreenUiAction.ViewRecipe -> viewRecipe(uiAction)

            is HomeScreenUiAction.SaveOrRemoveRecipe -> setSavedRecipeState(
                recipeId = uiAction.recipeId,
                saved = uiAction.saved
            )

        }
    }

    private fun viewRecipe(uiAction: HomeScreenUiAction.ViewRecipe) {
        viewModelScope.launch {
            val lastViewedTimestamp = System.currentTimeMillis()
            withContext(Dispatchers.IO) {
                repository.updateRecipeLastViewedTimestamp(
                    recipeId = uiAction.recipeId,
                    timestamp = lastViewedTimestamp
                )
                withContext(Dispatchers.Default) {
                    sendUiAction(uiAction)
                }
            }
        }
    }

    private fun getRecipesByCategory(category: RecipesCategory) {
        viewModelScope.launch {
            categoryLoadJob?.cancel()
            categoryLoadJob = this.launch {
                withContext(Dispatchers.IO) {
                    repository.getRecipesByCategory(category).onEach { recipes ->
                        withContext(Dispatchers.Default) {
                            _categorizedRecipes.update { recipes }
                            _state.update { it.copy(isLoadingCategory = false) }
                        }
                    }.launchIn(this)
                }
            }
        }
    }

    private fun setSavedRecipeState(recipeId: String, saved: Boolean) {
        viewModelScope.launch {
            val timestamp = if (saved) System.currentTimeMillis() else null
            withContext(Dispatchers.IO) {
                repository.updateRecipeSavedState(
                    recipeId = recipeId,
                    saved = saved,
                    timestamp = timestamp
                ).onEach { onSetSavedRecipeState(it) }.launchIn(this)
            }
        }
    }

    private suspend fun onSetSavedRecipeState(result: Result<Unit>) {
        withContext(Dispatchers.Default) {
            when (result) {
                is Result.Error -> Unit // todo
                else -> Unit
            }
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