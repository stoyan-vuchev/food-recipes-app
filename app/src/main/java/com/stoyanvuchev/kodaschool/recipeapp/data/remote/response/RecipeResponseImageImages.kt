package com.stoyanvuchev.kodaschool.recipeapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeResponseImageImages(
    @SerializedName("LARGE")
    val large: RecipeResponseImage,
    @SerializedName("REGULAR")
    val regular: RecipeResponseImage,
    @SerializedName("SMALL")
    val small: RecipeResponseImage,
    @SerializedName("THUMBNAIL")
    val thumbnail: RecipeResponseImage
)