package com.stoyanvuchev.kodaschool.recipeapp.data.repository

import com.stoyanvuchev.kodaschool.recipeapp.core.utils.Result
import com.stoyanvuchev.kodaschool.recipeapp.data.local.LocalDatabaseDao
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

    override suspend fun getRecipesByCategory(
        category: RecipesCategory
    ): Flow<List<RecipeModel>> {

        if (dao.getRecipesByCategory(category).isEmpty()) {
            getRecipesByCategoryFromRemoteDataSourceAPI(category)
        }

        return dao.observeRecipesByCategory(category).map {
            it.map { entity -> entity.toRecipeModel() }
        }

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
            dao.upsertRecipe(recipeModel.toRecipeEntity())
            trySend(Result.Success(Unit))
        } catch (e: Exception) {
            trySend(Result.Error(Result.uiStringError(e.message)))
        }
        awaitClose { }
    }

    private suspend fun getRecipesByCategoryFromRemoteDataSourceAPI(
        category: RecipesCategory
    ): Result<Unit> = try {
        val response = api.searchByCategory(category = category.stringValue)
        if (response.isSuccessful) {

            val responseBody = response.body()
            if (responseBody != null) {

                val recipes = responseBody.hits.map { it.recipe.toRecipeEntity() }
                dao.insertRecipes(recipes)

                if (dao.getRecipesByCategory(category).isNotEmpty()) {
                    Result.Success(Unit)
                } else {
                    Result.Error(Result.uiStringError())
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

    private suspend fun getRecipeFromRemoteDataSourceAPI(
        recipeId: String
    ): Result<RecipeModel> = try {
        val response = api.getRecipeById(recipeId = recipeId)
        if (response.isSuccessful) {

            val responseBody = response.body()
            if (responseBody != null) {

                val recipeEntity = responseBody.toRecipeEntity()
                dao.upsertRecipe(recipeEntity)

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