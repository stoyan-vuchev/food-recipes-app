package com.stoyanvuchev.kodaschool.recipeapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeResponseIngredient(
    @SerializedName("food")
    val food: String,
    @SerializedName("measure")
    val measure: String,
    @SerializedName("quantity")
    val quantity: Double,
    @SerializedName("text")
    val text: String,
    @SerializedName("weight")
    val weight: Double
)