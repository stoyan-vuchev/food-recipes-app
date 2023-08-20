package com.stoyanvuchev.kodaschool.recipeapp.presentation.recently_viewed

import androidx.compose.runtime.Immutable
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory

@Immutable
sealed interface RecentlyViewedScreenUiAction {
    data object GoBack : RecentlyViewedScreenUiAction
    data class SetCategory(val category: RecipesCategory) : RecentlyViewedScreenUiAction
    data class SetSaved(val isSaved: Boolean, val recipeId: String) : RecentlyViewedScreenUiAction
    data class ViewRecipe(val recipeId: String) : RecentlyViewedScreenUiAction
}