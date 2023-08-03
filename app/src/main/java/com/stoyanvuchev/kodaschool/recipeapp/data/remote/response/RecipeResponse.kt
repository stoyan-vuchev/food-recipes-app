package com.stoyanvuchev.kodaschool.recipeapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("images")
    val images: RecipeResponseImageImages,
    @SerializedName("ingredients")
    val ingredients: List<RecipeResponseIngredient>,
    @SerializedName("label")
    val label: String,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("url")
    val url: String
)