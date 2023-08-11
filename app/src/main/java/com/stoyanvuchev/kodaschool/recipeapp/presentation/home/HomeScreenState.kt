package com.stoyanvuchev.kodaschool.recipeapp.presentation.home

import androidx.compose.runtime.Stable
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel

@Stable
data class HomeScreenState(
    val isLoadingCategory: Boolean = true,
    val category: RecipesCategory = RecipesCategory.Default,
    val categories: List<RecipesCategory> = RecipesCategory.categoryList.drop(1),
    val categoryRecipesList: List<RecipeModel> = emptyList()
)