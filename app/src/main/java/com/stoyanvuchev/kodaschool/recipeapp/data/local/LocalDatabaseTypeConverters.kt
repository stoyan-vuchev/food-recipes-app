package com.stoyanvuchev.kodaschool.recipeapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeIngredient

class LocalDatabaseTypeConverters {

    @TypeConverter
    fun fromRecipesCategoryToJson(src: RecipesCategory): String {
        val type = object : TypeToken<RecipesCategory>() {}.type
        return Gson().toJson(src, type)
    }

    @TypeConverter
    fun fromJsonToRecipesCategory(src: String): RecipesCategory {
        val type = object : TypeToken<RecipesCategory>() {}.type
        return Gson().fromJson(src, type)
    }

    @TypeConverter
    fun fromListOfRecipeIngredientToJson(src: List<RecipeIngredient>): String {
        val type = object : TypeToken<List<RecipeIngredient>>() {}.type
        return Gson().toJson(src, type)
    }

    @TypeConverter
    fun fromJsonToListOfRecipeIngredient(src: String): List<RecipeIngredient> {
        val type = object : TypeToken<List<RecipeIngredient>>() {}.type
        return Gson().fromJson(src, type)
    }

    @TypeConverter
    fun fromByteArrayToJson(src: ByteArray?): String {
        val type = object : TypeToken<ByteArray?>() {}.type
        return Gson().toJson(src, type)
    }

    @TypeConverter
    fun fromJsonToByteArray(src: String): ByteArray? {
        val type = object : TypeToken<ByteArray?>() {}.type
        return Gson().fromJson(src, type)
    }

}