package com.stoyanvuchev.kodaschool.recipeapp.presentation.recipe

import androidx.compose.runtime.Stable
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel

@Stable
data class RecipeScreenState(
    val isLoading: Boolean = true,
    val recipe: RecipeModel = RecipeModel()
)