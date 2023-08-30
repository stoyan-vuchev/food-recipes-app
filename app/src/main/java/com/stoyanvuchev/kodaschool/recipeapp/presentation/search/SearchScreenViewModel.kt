package com.stoyanvuchev.kodaschool.recipeapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.Result
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.UiString
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
class SearchScreenViewModel @Inject constructor(
    private val repository: RecipesRepository
) : ViewModel() {

    private val _screenState = MutableStateFlow(SearchScreenState())
    val screenState = _screenState.asStateFlow()

    private val _uiActionChannel = Channel<SearchScreenUiAction>()
    val uiActionFlow = _uiActionChannel.receiveAsFlow()

    private var categoryDebounceJob: Job? = null
    private var searchJob: Job? = null

    fun onUiAction(uiAction: SearchScreenUiAction) {
        when (uiAction) {
            is SearchScreenUiAction.Search -> searchForRecipes()
            is SearchScreenUiAction.SetCategory -> setCategory(uiAction)
            is SearchScreenUiAction.SetSearchQueryText -> setSearchQueryText(uiAction)
            is SearchScreenUiAction.ViewRecipe -> sendUiAction(uiAction)
        }
    }

    private fun setCategory(uiAction: SearchScreenUiAction.SetCategory) {
        _screenState.update { it.copy(category = uiAction.category) }
        viewModelScope.launch {
            categoryDebounceJob?.cancel()
            categoryDebounceJob = this.launch {
                delay(600L)
                searchForRecipes()
            }
        }
    }

    private fun setSearchQueryText(uiAction: SearchScreenUiAction.SetSearchQueryText) {
        _screenState.update { it.copy(searchQueryText = uiAction.query) }
    }

    private fun searchForRecipes() {
        val category = _screenState.value.category
        val query = _screenState.value.searchQueryText.trim()
        if (query.isNotBlank()) {
            viewModelScope.launch {
                searchJob?.cancel()
                searchJob = this.launch {
                    withContext(Dispatchers.IO) {
                        repository.searchForRecipes(
                            category = category,
                            query = query
                        ).onEach { onSearchForRecipesResult(it) }.launchIn(this)
                    }
                }
            }
        }
    }

    private suspend fun onSearchForRecipesResult(result: Result<List<RecipeModel>>) {
        withContext(Dispatchers.Default) {
            when (result) {

                is Result.Loading -> _screenState.update {
                    it.copy(
                        isSearching = true,
                        isSearchComplete = false
                    )
                }

                is Result.Success -> _screenState.update {
                    it.copy(
                        error = UiString.EmptyString,
                        searchResults = result.data,
                        isSearching = false,
                        isSearchComplete = true
                    )
                }

                is Result.Error -> _screenState.update {
                    it.copy(
                        searchResults = emptyList(),
                        error = result.error,
                        isSearching = false,
                        isSearchComplete = true
                    )
                }

            }
        }
    }

    private fun sendUiAction(uiAction: SearchScreenUiAction) {
        viewModelScope.launch { _uiActionChannel.send(uiAction) }
    }

}