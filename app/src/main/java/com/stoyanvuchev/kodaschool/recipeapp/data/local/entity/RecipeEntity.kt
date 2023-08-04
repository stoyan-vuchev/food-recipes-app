package com.stoyanvuchev.kodaschool.recipeapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeIngredient

@Entity(tableName = "recipes_table", primaryKeys = ["recipe_id"])
data class RecipeEntity(

    @ColumnInfo(name = "recipe_id")
    val recipeId: String,

    @ColumnInfo(name = "is_bookmarked")
    val isBookmarked: Boolean,
    @ColumnInfo(name = "bookmark_timestamp")
    val bookmarkTimestamp: Long?,

    val label: String,
    val category: RecipesCategory,
    val ingredients: List<RecipeIngredient>,
    val source: String,
    val url: String,

    @ColumnInfo(name = "image_thumbnail")
    val imageThumbnail: String,
    @ColumnInfo(name = "image_small")
    val imageSmall: String,
    @ColumnInfo(name = "image_regular")
    val imageRegular: String,
    @ColumnInfo(name = "image_large")
    val imageLarge: String

)