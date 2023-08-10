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
    fun fromStringListToJson(src: List<String>): String {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().toJson(src, type)
    }

    @TypeConverter
    fun fromJsonToStringList(src: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(src, type)
    }

}