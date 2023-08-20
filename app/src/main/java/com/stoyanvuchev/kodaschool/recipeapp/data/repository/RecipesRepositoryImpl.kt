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

    override fun getRecentRecipes(count: Int): Flow<List<RecipeModel>> {
        return dao.getRecentRecipes(count).map { list ->
            list.distinctBy { it.recipeId }.map { it.toRecipeModel() }
        }
    }

    override suspend fun getRecentRecipesByCategory(
        category: RecipesCategory
    ): Result<List<RecipeModel>> {
        return try {

            val recipes = dao.getRecentRecipesByCategory(category)
                .distinctBy { it.recipeId }
                .map { it.toRecipeModel() }

            Result.Success(recipes)

        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(Result.uiStringError(e.message))
        }
    }

    override suspend fun getRecipesByCategory(
        category: RecipesCategory
    ): Flow<List<RecipeModel>> {

        if (dao.getByCategoryFirstCheck(category) == null) {
            getRecipesByCategoryFromRemoteDataSourceAPI(category = category)
        }

        return dao.getRecipesByCategory(category).map { list ->
            list.distinctBy { it.recipeId }.map { it.toRecipeModel() }
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
                trySend(getRecipeFromRemoteDataSourceAPI(recipeId))
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
        recipeId: String,
        saved: Boolean,
        timestamp: Long?
    ): Flow<Result<Unit>> = callbackFlow {
        trySend(Result.Loading())
        try {

            dao.updateRecipeSavedState(
                recipeId = recipeId,
                saved = saved,
                timestamp = timestamp
            )

            trySend(Result.Success(Unit))

        } catch (e: Exception) {
            e.printStackTrace()
            trySend(Result.Error(Result.uiStringError(e.message)))
        }
        awaitClose { }
    }

    override suspend fun updateRecipeLastViewedTimestamp(
        recipeId: String,
        timestamp: Long?
    ) = try {
        dao.updateRecipeLastViewedTimestamp(
            recipeId = recipeId,
            timestamp = timestamp
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }

    override suspend fun searchForRecipes(
        category: RecipesCategory,
        query: String
    ): Flow<Result<List<RecipeModel>>> = callbackFlow {
        trySend(Result.Loading())
        try {

            val response = if (category == RecipesCategory.All) {
                api.searchRecipesWithoutCategory(query = query)
            } else api.searchRecipesWithCategory(
                category = category.stringValue,
                query = query
            )

            if (response.isSuccessful) {

                val result = response.body()
                if (result != null) {

                    val timestamp = System.currentTimeMillis()
                    val recipes = result.hits.map { it.recipe.toRecipeModel(timestamp) }

                    trySend(Result.Success(recipes))

                } else {
                    trySend(Result.Error(Result.uiStringError()))
                }

            } else {
                trySend(Result.Error(Result.uiStringError()))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            trySend(Result.Error(Result.uiStringError(e.message)))
        }
        awaitClose { }
    }

    private suspend fun getRecipesByCategoryFromRemoteDataSourceAPI(
        category: RecipesCategory
    ): Result<Unit> = try {
        val response = api.searchRecipesWithCategory(category = category.stringValue)
        if (response.isSuccessful) {

            val responseBody = response.body()
            if (responseBody != null) {

                val timestamp = System.currentTimeMillis()
                val recipes = responseBody.hits.map {
                    it.recipe.toRecipeEntity(timestamp, category)
                }

                dao.insertRecipes(recipes)
                Result.Success(Unit)

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
    ): Result<RecipeModel> {
        return try {
            val response = api.getRecipeById(recipeId = recipeId)
            if (response.isSuccessful) {

                val responseBody = response.body()
                if (responseBody != null) {

                    val timestamp = System.currentTimeMillis()
                    val recipeEntity = responseBody.recipe.toRecipeEntity(timestamp)

                    dao.insertRecipe(recipeEntity)
                    dao.updateRecipeLastViewedTimestamp(recipeEntity.recipeId, timestamp)

                    val recipe = dao.getRecipeById(recipeId)?.toRecipeModel()
                    if (recipe != null) {
                        Result.Success(recipe)
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
    }

}