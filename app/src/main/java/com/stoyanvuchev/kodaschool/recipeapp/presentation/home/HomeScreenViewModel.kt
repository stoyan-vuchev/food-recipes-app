package com.stoyanvuchev.kodaschool.recipeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private var categoryDebounceJob: Job? = null

    init {
        viewModelScope.launch {
            delay(256L)
            getRecipesByCategory(category = RecipesCategory.Default)
        }
    }

    fun onUiAction(uiAction: HomeScreenUiAction) {
        when (uiAction) {

            is HomeScreenUiAction.SetCategory -> setCategory(uiAction.category)
            is HomeScreenUiAction.ViewRecipe -> sendUiAction(uiAction)

            is HomeScreenUiAction.SaveOrRemoveRecipe -> {
                // todo
            }

        }
    }

    private fun getRecipesByCategory(category: RecipesCategory) {
        viewModelScope.launch(Dispatchers.IO) {
            val recipes = repository.getRecipesByCategory(category)
            onGetRecipesByCategoryResult(recipes)
        }
    }

    private suspend fun onGetRecipesByCategoryResult(recipes: List<RecipeModel>) {
        withContext(Dispatchers.Default) {
            _state.update {
                it.copy(
                    categoryRecipesList = recipes,
                    isLoadingCategory = false
                )
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