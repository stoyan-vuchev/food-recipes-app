package com.stoyanvuchev.kodaschool.recipeapp.domain.model

import android.graphics.Bitmap
import androidx.compose.runtime.Stable
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory

@Stable
data class RecipeModel(
    val recipeId: String = "",
    val isBookmarked: Boolean = false,
    val bookmarkTimestamp: Long? = null,
    val timestamp: Long = 0L,
    val label: String = "",
    val category: RecipesCategory = RecipesCategory.Default,
    val ingredients: List<RecipeIngredient> = emptyList(),
    val source: String = "",
    val url: String = "",
    val imageThumbnail: String = "",
    val imageSmall: String = "",
    val imageRegular: String = "",
    val imageLarge: String = "",
    val thumbnail: Bitmap? = null
)