package com.stoyanvuchev.kodaschool.recipeapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class Next(
    @SerializedName("href")
    val href: String,
    @SerializedName("title")
    val title: String
)