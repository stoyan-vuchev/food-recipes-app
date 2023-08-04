package com.stoyanvuchev.kodaschool.recipeapp.domain.model

import androidx.compose.runtime.Stable
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory

@Stable
data class RecipeModel(
    val recipeId: String,
    val isBookmarked: Boolean,
    val bookmarkTimestamp: Long?,
    val label: String,
    val category: RecipesCategory,
    val ingredients: List<RecipeIngredient>,
    val source: String,
    val url: String,
    val imageThumbnail: String,
    val imageSmall: String,
    val imageRegular: String,
    val imageLarge: String
)