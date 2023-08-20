package com.stoyanvuchev.kodaschool.recipeapp.presentation.search

import androidx.compose.runtime.Immutable
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory

@Immutable
sealed interface SearchScreenUiAction {
    data object Search : SearchScreenUiAction
    data class SetCategory(val category: RecipesCategory) : SearchScreenUiAction
    data class SetSearchQueryText(val query: String) : SearchScreenUiAction
    data class ViewRecipe(val recipeId: String) : SearchScreenUiAction
}