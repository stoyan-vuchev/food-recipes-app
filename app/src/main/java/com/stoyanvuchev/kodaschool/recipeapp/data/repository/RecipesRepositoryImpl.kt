package com.stoyanvuchev.kodaschool.recipeapp.data.repository

import com.stoyanvuchev.kodaschool.recipeapp.core.utils.Result
import com.stoyanvuchev.kodaschool.recipeapp.data.local.LocalDatabaseDao
import com.stoyanvuchev.kodaschool.recipeapp.data.local.entity.RecipeEntity
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.RemoteDataSourceAPI
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel
import com.stoyanvuchev.kodaschool.recipeapp.domain.repository.RecipesRepository
import com.stoyanvuchev.kodaschool.recipeapp.mappers.toRecipeEntity
import com.stoyanvuchev.kodaschool.recipeapp.mappers.toRecipeModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val api: RemoteDataSourceAPI,
    private val dao: LocalDatabaseDao
) : RecipesRepository {

    override suspend fun getRecentRecipes(count: Int): Flow<List<RecipeModel>> {
        return dao.getRecentRecipes(count).map { list -> list.map { it.toRecipeModel() } }
    }

    override suspend fun getRecentRecipesByCategory(
        category: RecipesCategory
    ): Result<List<RecipeModel>> {
        return try {
            val recipes = dao.getRecentRecipesByCategory(category).map { it.toRecipeModel() }
            Result.Success(recipes)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(Result.uiStringError(e.message))
        }
    }

    override suspend fun getRecipesByCategory(
        category: RecipesCategory
    ): List<RecipeModel> {

        val recipes = dao.getRecipesByCategory(category)
        val first = recipes.firstOrNull()

        return if (recipes.isNotEmpty() && first != null) recipes.map { it.toRecipeModel() }
        else when (val result = getRecipesByCategoryFromRemoteDataSourceAPI(category)) {
            is Result.Success -> result.data.map { it.toRecipeModel() }
            else -> emptyList()
        }

    }

    override suspend fun getSavedRecipesByCategory(category: RecipesCategory): List<RecipeModel> {
        return dao.getSavedRecipesByCategory(category).map { it.toRecipeModel() }
    }

    override suspend fun getRecipeById(
        recipeId: String
    ): Flow<Result<RecipeModel>> = callbackFlow {
        trySend(Result.Loading())
        try {
            val entity = dao.getRecipeById(recipeId)
            if (entity != null) {
                trySend(Result.Success(entity.toRecipeModel()))
            } else {
                getRecipeFromRemoteDataSourceAPI(recipeId)
            }
        } catch (e: Exception) {
            trySend(Result.Error(Result.uiStringError(e.message)))
        }
        awaitClose { }
    }

    override suspend fun updateRecipe(
        recipeModel: RecipeModel
    ): Flow<Result<Unit>> = callbackFlow {
        trySend(Result.Loading())
        try {
            dao.updateRecipe(recipeModel.toRecipeEntity())
            trySend(Result.Success(Unit))
        } catch (e: Exception) {
            trySend(Result.Error(Result.uiStringError(e.message)))
        }
        awaitClose { }
    }

    override suspend fun updateRecipeSavedState(
        recipe: RecipeEntity,
        saved: Boolean,
        timestamp: Long?
    ): Flow<Result<Unit>> = callbackFlow {
        trySend(Result.Loading())
        try {
            dao.updateRecipeSavedState(recipe, saved, timestamp)
            trySend(Result.Success(Unit))
        } catch (e: Exception) {
            e.printStackTrace()
            trySend(Result.Error(Result.uiStringError(e.message)))
        }
        awaitClose { }
    }

    private suspend fun getRecipesByCategoryFromRemoteDataSourceAPI(
        category: RecipesCategory
    ): Result<List<RecipeEntity>> = try {
        val response = api.searchByCategory(category = category.stringValue)
        if (response.isSuccessful) {

            val responseBody = response.body()
            if (responseBody != null) {

                val timestamp = System.currentTimeMillis()
                val recipes = responseBody.hits.map {
                    it.recipe.toRecipeEntity(timestamp, category)
                }

                dao.insertRecipes(recipes)
                Result.Success(recipes)

            } else {
                Result.Error(Result.uiStringError())
            }

        } else {
            Result.Error(Result.uiStringError())
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Result.Error(Result.uiStringError(e.message))
    }

    private suspend fun getRecipeFromRemoteDataSourceAPI(
        recipeId: String
    ): Result<RecipeModel> = try {
        val response = api.getRecipeById(recipeId = recipeId)
        if (response.isSuccessful) {

            val responseBody = response.body()
            if (responseBody != null) {

                val timestamp = System.currentTimeMillis()
                val recipeEntity = responseBody.toRecipeEntity(timestamp)
                dao.updateRecipe(recipeEntity)

                val entity = dao.getRecipeById(recipeId)
                if (entity != null) {
                    Result.Success(entity.toRecipeModel())
                } else {
                    Result.Success(recipeEntity.toRecipeModel())
                }

            } else {
                Result.Error(Result.uiStringError())
            }

        } else {
            Result.Error(Result.uiStringError())
        }
    } catch (e: Exception) {
        Result.Error(Result.uiStringError(e.message))
    }

}