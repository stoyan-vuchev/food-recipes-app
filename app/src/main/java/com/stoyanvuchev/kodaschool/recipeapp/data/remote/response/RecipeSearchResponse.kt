package com.stoyanvuchev.kodaschool.recipeapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(
    @SerializedName("hits")
    val hits: List<RecipeSearchResponseHit>
)