package com.stoyanvuchev.kodaschool.recipeapp.presentation.saved

import androidx.compose.runtime.Stable
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel

@Stable
data class SavedRecipesScreenState(
    val isLoading: Boolean = true,
    val category: RecipesCategory = RecipesCategory.All,
    val categories: List<RecipesCategory> = RecipesCategory.categoryList,
    val recipes: List<RecipeModel> = emptyList()
)