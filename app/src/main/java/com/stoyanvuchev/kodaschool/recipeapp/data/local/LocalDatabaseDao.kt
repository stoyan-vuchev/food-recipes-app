package com.stoyanvuchev.kodaschool.recipeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.stoyanvuchev.kodaschool.recipeapp.data.local.entity.RecipeEntity
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory

@Dao
interface LocalDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Query("SELECT * FROM recipes_table WHERE category LIKE :category")
    suspend fun getRecipesByCategory(category: RecipesCategory): List<RecipeEntity>

    @Query("SELECT * FROM recipes_table WHERE recipe_id LIKE :recipeId LIMIT 1")
    suspend fun getRecipeById(recipeId: String): RecipeEntity?

    @Upsert
    suspend fun upsertRecipe(recipeEntity: RecipeEntity)

    @Upsert
    suspend fun upsertRecipes(recipes: List<RecipeEntity>)

}