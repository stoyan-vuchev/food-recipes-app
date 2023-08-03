package com.stoyanvuchev.kodaschool.recipeapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeSearchResponseLinks(
    @SerializedName("self")
    val self: Self
)