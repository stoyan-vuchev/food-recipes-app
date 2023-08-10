package com.stoyanvuchev.kodaschool.recipeapp.presentation.recipe

import androidx.compose.runtime.Immutable

@Immutable
sealed interface RecipeScreenUiAction {
    data object NavigateBack : RecipeScreenUiAction
    data class SaveRecipe(val saved: Boolean) : RecipeScreenUiAction
    data class ViewFullRecipe(val url: String) : RecipeScreenUiAction
}