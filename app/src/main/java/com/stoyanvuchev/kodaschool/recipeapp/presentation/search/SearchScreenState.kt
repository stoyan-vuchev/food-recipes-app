package com.stoyanvuchev.kodaschool.recipeapp.presentation.search

import androidx.compose.runtime.Stable
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.UiString
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel

@Stable
data class SearchScreenState(
    val isSearching: Boolean = false,
    val isSearchComplete: Boolean = false,
    val category: RecipesCategory = RecipesCategory.All,
    val categories: List<RecipesCategory> = RecipesCategory.categoryList,
    val searchQueryText: String = "",
    val searchResults: List<RecipeModel> = emptyList(),
    val error: UiString = UiString.EmptyString
)