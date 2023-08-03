package com.stoyanvuchev.kodaschool.recipeapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("from")
    val from: Int,
    @SerializedName("hits")
    val hits: List<RecipeSearchResponseHit>,
    @SerializedName("_links")
    val links: LinksX,
    @SerializedName("to")
    val to: Int
)