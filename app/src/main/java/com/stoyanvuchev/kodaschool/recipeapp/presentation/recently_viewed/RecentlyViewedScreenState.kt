package com.stoyanvuchev.kodaschool.recipeapp.presentation.recently_viewed

import androidx.compose.runtime.Stable
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.UiString
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel

@Stable
data class RecentlyViewedScreenState(
    val isLoading: Boolean = true,
    val category: RecipesCategory = RecipesCategory.All,
    val categories: List<RecipesCategory> = RecipesCategory.categoryList,
    val recipes: List<RecipeModel> = emptyList(),
    val error: UiString = UiString.EmptyString
)