package com.stoyanvuchev.kodaschool.recipeapp.domain.repository

import com.stoyanvuchev.kodaschool.recipeapp.core.utils.Result
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    suspend fun getRecipesByCategory(category: RecipesCategory): List<RecipeModel>
    suspend fun getRecipeById(recipeId: String): Flow<Result<RecipeModel>>
    suspend fun updateRecipe(recipeModel: RecipeModel): Flow<Result<Unit>>
}