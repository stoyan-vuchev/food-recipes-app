package com.stoyanvuchev.kodaschool.recipeapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeResponseImage(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)