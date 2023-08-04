package com.stoyanvuchev.kodaschool.recipeapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeIngredient

data class RecipeResponse(
    @SerializedName("images")
    val images: RecipeResponseImages,
    @SerializedName("ingredients")
    val ingredients: List<RecipeIngredient>,
    @SerializedName("label")
    val label: String,
    @SerializedName("mealType")
    val category: ArrayList<String>,
    @SerializedName("source")
    val source: String,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("url")
    val url: String
)