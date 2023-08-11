package com.stoyanvuchev.kodaschool.recipeapp.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
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
class SavedRecipesScreenViewModel @Inject constructor(
    private val repository: RecipesRepository
) : ViewModel() {

    private val _screenState = MutableStateFlow(SavedRecipesScreenState())
    val screenState = _screenState.asStateFlow()

    private val _uiActionChannel = Channel<SavedRecipesScreenUiAction>()
    val uiActionFlow = _uiActionChannel.receiveAsFlow()

    private var debounceJob: Job? = null

    init {
        viewModelScope.launch {
            delay(500L)
            getSavedRecipes(category = RecipesCategory.All)
        }
    }

    fun onUiAction(uiAction: SavedRecipesScreenUiAction) {
        when (uiAction) {
            is SavedRecipesScreenUiAction.SetCategory -> setCategory(uiAction)
            is SavedRecipesScreenUiAction.SetSaved -> saveRecipe(uiAction)
            is SavedRecipesScreenUiAction.ViewRecipe -> sendUiAction(uiAction)
        }
    }

    private fun setCategory(uiAction: SavedRecipesScreenUiAction.SetCategory) {

        _screenState.update {
            it.copy(
                isLoading = true,
                category = uiAction.category
            )
        }

        viewModelScope.launch {
            debounceJob?.cancel()
            debounceJob = this.launch {
                delay(500L)
                getSavedRecipes(uiAction.category)
            }
        }

    }

    private fun getSavedRecipes(category: RecipesCategory) {
        viewModelScope.launch(Dispatchers.IO) {
            val recipes = repository.getSavedRecipesByCategory(category)
            withContext(Dispatchers.Default) {
                _screenState.update {
                    it.copy(
                        recipes = recipes,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun saveRecipe(uiAction: SavedRecipesScreenUiAction.SetSaved) {

        _screenState.value.recipes.find { it.recipeId == uiAction.recipeId }?.let { recipe ->

            val timestamp = if (uiAction.saved) System.currentTimeMillis() else null
            val updatedRecipe = recipe.copy(
                isBookmarked = uiAction.saved,
                bookmarkTimestamp = timestamp
            )

            viewModelScope.launch(Dispatchers.IO) {
                repository.updateRecipe(updatedRecipe).onEach { }.launchIn(this)
            }

        }

    }

    private fun sendUiAction(uiAction: SavedRecipesScreenUiAction) {
        viewModelScope.launch { _uiActionChannel.send(uiAction) }
    }

}