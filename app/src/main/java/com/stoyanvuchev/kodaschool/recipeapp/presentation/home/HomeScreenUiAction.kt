package com.stoyanvuchev.kodaschool.recipeapp.presentation.home

import androidx.compose.runtime.Immutable
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory

@Immutable
sealed interface HomeScreenUiAction {

    data object Search : HomeScreenUiAction
    data object SeeAllRecentlyViewed : HomeScreenUiAction
    data class SetCategory(val category: RecipesCategory) : HomeScreenUiAction
    data class ViewRecipe(val recipeId: String) : HomeScreenUiAction

    data class SaveOrRemoveRecipe(
        val recipeId: String,
        val saved: Boolean
    ) : HomeScreenUiAction

}