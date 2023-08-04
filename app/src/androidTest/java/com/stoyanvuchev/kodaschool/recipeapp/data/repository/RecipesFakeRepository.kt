package com.stoyanvuchev.kodaschool.recipeapp.data.repository

import com.stoyanvuchev.kodaschool.recipeapp.core.utils.Result
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.Result.Companion.uiStringError
import com.stoyanvuchev.kodaschool.recipeapp.data.local.LocalDatabaseDao
import com.stoyanvuchev.kodaschool.recipeapp.data.local.entity.RecipeEntity
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel
import com.stoyanvuchev.kodaschool.recipeapp.domain.repository.RecipesRepository
import com.stoyanvuchev.kodaschool.recipeapp.mappers.toRecipeEntity
import com.stoyanvuchev.kodaschool.recipeapp.mappers.toRecipeModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map

class RecipesFakeRepository(
    private val dao: LocalDatabaseDao
) : RecipesRepository {

    override suspend fun getRecipesByCategory(
        category: RecipesCategory
    ): Flow<List<RecipeModel>> {
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

                dao.upsertRecipe(fakeRecipes.find { it.recipeId == recipeId }!!)

                val newEntity = dao.getRecipeById(recipeId)
                if (newEntity != null) trySend(Result.Success(newEntity.toRecipeModel()))
                else trySend(Result.Error(uiStringError()))

            }
        } catch (e: Exception) {
            trySend(Result.Error(uiStringError(e.message)))
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
            trySend(Result.Error(uiStringError(e.message)))
        }
        awaitClose { }
    }

    companion object {

        val fakeRecipes = listOf(
            RecipeEntity(
                recipeId = "recipe_d04a8f9d3b6662a802c17e1a2e5f0f1b",
                isBookmarked = false,
                bookmarkTimestamp = null,
                label = "",
                category = RecipesCategory.Breakfast,
                source = "",
                ingredients = emptyList(),
                url = "",
                imageThumbnail = "",
                imageSmall = "",
                imageRegular = "",
                imageLarge = ""
            ),
            RecipeEntity(
                recipeId = "recipe_afe7f762a186efb95475c6c29d5da39b",
                isBookmarked = false,
                bookmarkTimestamp = null,
                label = "",
                category = RecipesCategory.Lunch,
                source = "",
                ingredients = emptyList(),
                url = "",
                imageThumbnail = "",
                imageSmall = "",
                imageRegular = "",
                imageLarge = ""
            ),
            RecipeEntity(
                recipeId = "recipe_452e9fce1999537b9e5698fda667c75f",
                isBookmarked = false,
                bookmarkTimestamp = null,
                label = "",
                category = RecipesCategory.Dinner,
                source = "",
                ingredients = emptyList(),
                url = "",
                imageThumbnail = "",
                imageSmall = "",
                imageRegular = "",
                imageLarge = ""
            )
        )

    }

}