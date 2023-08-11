package com.stoyanvuchev.kodaschool.recipeapp.presentation.saved

import androidx.compose.runtime.Immutable
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory

@Immutable
sealed interface SavedRecipesScreenUiAction {
    data class SetCategory(val category: RecipesCategory) : SavedRecipesScreenUiAction
    data class SetSaved(val saved: Boolean, val recipeId: String) : SavedRecipesScreenUiAction
    data class ViewRecipe(val recipeId: String) : SavedRecipesScreenUiAction
}