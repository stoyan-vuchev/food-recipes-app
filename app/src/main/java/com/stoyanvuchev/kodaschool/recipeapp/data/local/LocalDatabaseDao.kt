package com.stoyanvuchev.kodaschool.recipeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.stoyanvuchev.kodaschool.recipeapp.data.local.entity.RecipeEntity
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Query("SELECT * FROM recipes_table WHERE category = :category")
    suspend fun getRecipesByCategory(category: RecipesCategory): List<RecipeEntity>

    @Query("SELECT * FROM recipes_table WHERE category = :category")
    fun observeRecipesByCategory(category: RecipesCategory): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipes_table WHERE recipe_id = :recipeId LIMIT 1")
    suspend fun getRecipeById(recipeId: String): RecipeEntity?

    @Upsert
    suspend fun upsertRecipe(recipeEntity: RecipeEntity)

}