package com.stoyanvuchev.kodaschool.recipeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.stoyanvuchev.kodaschool.recipeapp.data.local.entity.RecipeEntity
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipe(recipeEntity: RecipeEntity)

    @Query(
        "SELECT * FROM recipes_table " +
                "WHERE last_viewed_timestamp IS NOT NULL " +
                "ORDER BY last_viewed_timestamp DESC LIMIT :count"
    )
    fun getRecentRecipes(count: Int): Flow<List<RecipeEntity>>

    suspend fun getRecentRecipesByCategory(
        category: RecipesCategory
    ) = if (category == RecipesCategory.All) getAllRecentRecipesDao()
    else getRecentRecipesByCategoryDao(category)

    @Query(
        "SELECT * FROM recipes_table " +
                "WHERE last_viewed_timestamp IS NOT NULL " +
                "AND category = :category " +
                "ORDER BY last_viewed_timestamp DESC"
    )
    suspend fun getRecentRecipesByCategoryDao(category: RecipesCategory): List<RecipeEntity>

    @Query(
        "SELECT * FROM recipes_table " +
                "WHERE last_viewed_timestamp IS NOT NULL " +
                "ORDER BY last_viewed_timestamp DESC"
    )
    suspend fun getAllRecentRecipesDao(): List<RecipeEntity>

    @Query("SELECT * FROM recipes_table WHERE category = :category LIMIT 1")
    suspend fun getByCategoryFirstCheck(category: RecipesCategory): RecipeEntity?

    @Query("SELECT * FROM recipes_table WHERE category = :category")
    fun getRecipesByCategory(category: RecipesCategory): Flow<List<RecipeEntity>>

    suspend fun getSavedRecipesByCategory(
        category: RecipesCategory
    ) = if (category == RecipesCategory.All) getAllSaved() else getSavedByCategory(category)

    @Query(
        "SELECT * FROM recipes_table " +
                "WHERE category LIKE :category " +
                "AND is_bookmarked IS 1 " +
                "AND bookmark_timestamp IS NOT NULL " +
                "ORDER BY bookmark_timestamp DESC"
    )
    suspend fun getSavedByCategory(category: RecipesCategory): List<RecipeEntity>

    @Query(
        "SELECT * FROM recipes_table " +
                "WHERE is_bookmarked IS 1 " +
                "AND bookmark_timestamp IS NOT NULL " +
                "ORDER BY bookmark_timestamp DESC"
    )
    suspend fun getAllSaved(): List<RecipeEntity>

    @Query("SELECT * FROM recipes_table WHERE recipe_id LIKE :recipeId LIMIT 1")
    suspend fun getRecipeById(recipeId: String): RecipeEntity?

    suspend fun updateRecipeSavedState(
        recipeId: String,
        saved: Boolean,
        timestamp: Long?
    ) {
        getRecipeById(recipeId)?.let { recipe ->
            updateRecipe(
                recipe.copy(
                    isBookmarked = saved,
                    bookmarkTimestamp = timestamp
                )
            )
        }
    }

    suspend fun updateRecipeLastViewedTimestamp(
        recipeId: String,
        timestamp: Long?
    ) {
        getRecipeById(recipeId)?.let { recipe ->
            updateRecipe(recipe.copy(lastViewedTimestamp = timestamp))
        }
    }

    @Update
    suspend fun updateRecipe(recipeEntity: RecipeEntity)

    @Upsert
    suspend fun upsertRecipes(recipes: List<RecipeEntity>)

}