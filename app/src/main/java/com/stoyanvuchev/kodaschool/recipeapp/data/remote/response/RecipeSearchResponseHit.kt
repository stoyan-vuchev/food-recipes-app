package com.stoyanvuchev.kodaschool.recipeapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeSearchResponseHit(
    @SerializedName("_links")
    val links: RecipeSearchResponseLinks,
    @SerializedName("recipe")
    val recipe: RecipeResponse
)