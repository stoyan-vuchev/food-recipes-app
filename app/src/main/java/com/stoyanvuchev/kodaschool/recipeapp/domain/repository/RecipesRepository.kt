package com.stoyanvuchev.kodaschool.recipeapp.domain.repository

import com.stoyanvuchev.kodaschool.recipeapp.core.utils.Result
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    fun getRecentRecipes(count: Int): Flow<List<RecipeModel>>
    suspend fun getRecentRecipesByCategory(category: RecipesCategory): Result<List<RecipeModel>>
    suspend fun getRecipesByCategory(category: RecipesCategory): Flow<List<RecipeModel>>
    suspend fun getSavedRecipesByCategory(category: RecipesCategory): List<RecipeModel>
    suspend fun getRecipeById(recipeId: String): Flow<Result<RecipeModel>>

    suspend fun updateRecipeSavedState(
        recipeId: String,
        saved: Boolean,
        timestamp: Long?
    ): Flow<Result<Unit>>

    suspend fun updateRecipe(recipeModel: RecipeModel): Flow<Result<Unit>>
    suspend fun updateRecipeLastViewedTimestamp(recipeId: String, timestamp: Long?)

    suspend fun searchForRecipes(
        category: RecipesCategory,
        query: String
    ): Flow<Result<List<RecipeModel>>>

}