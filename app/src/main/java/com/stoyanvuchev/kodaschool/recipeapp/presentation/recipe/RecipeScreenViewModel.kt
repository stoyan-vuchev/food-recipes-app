package com.stoyanvuchev.kodaschool.recipeapp.presentation.recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.Result
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel
import com.stoyanvuchev.kodaschool.recipeapp.domain.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
class RecipeScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: RecipesRepository
) : ViewModel() {

    private val _screenState = MutableStateFlow(RecipeScreenState())
    val screenState = _screenState.asStateFlow()

    private val _uiActionChannel = Channel<RecipeScreenUiAction>()
    val uiActionFlow = _uiActionChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>("recipeId")?.let {
                if (it.isNotBlank()) {
                    delay(256L)
                    getRecipe(it)
                }
            }
        }
    }

    fun onUiAction(uiAction: RecipeScreenUiAction) {
        when (uiAction) {
            is RecipeScreenUiAction.NavigateBack -> sendUiAction(uiAction)
            is RecipeScreenUiAction.SaveRecipe -> saveRecipe(uiAction)
            is RecipeScreenUiAction.ViewFullRecipe -> sendUiAction(uiAction)
        }
    }

    private fun saveRecipe(uiAction: RecipeScreenUiAction.SaveRecipe) {

        val saved = uiAction.saved
        val timestamp = if (saved) System.currentTimeMillis() else null
        val updatedRecipe = _screenState.value.recipe.copy(
            isBookmarked = saved,
            bookmarkTimestamp = timestamp
        )

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRecipe(updatedRecipe)
                .onEach { onSaveRecipeResult(it, saved, timestamp) }.launchIn(this)
        }

    }

    private suspend fun onSaveRecipeResult(
        result: Result<Unit>,
        saved: Boolean,
        timestamp: Long?
    ) = withContext(Dispatchers.Default) {
        if (result is Result.Success) _screenState.update {
            it.copy(
                recipe = it.recipe.copy(
                    isBookmarked = saved,
                    bookmarkTimestamp = timestamp
                )
            )
        } else Unit
    }

    private fun getRecipe(recipeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRecipeById(recipeId)
                .onEach { onGetRecipeResult(it) }.launchIn(this)
        }
    }

    private suspend fun onGetRecipeResult(result: Result<RecipeModel>) {
        withContext(Dispatchers.Default) {
            when (result) {

                is Result.Loading -> _screenState.update { it.copy(isLoading = true) }

                is Result.Success -> _screenState.update {
                    it.copy(
                        recipe = result.data,
                        isLoading = false
                    )
                }

                is Result.Error -> _screenState.update { it.copy(isLoading = false) }

            }
        }
    }

    private fun sendUiAction(uiAction: RecipeScreenUiAction) {
        viewModelScope.launch { _uiActionChannel.send(uiAction) }
    }

}