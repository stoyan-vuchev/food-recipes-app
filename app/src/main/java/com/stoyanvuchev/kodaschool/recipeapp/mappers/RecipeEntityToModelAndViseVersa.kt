package com.stoyanvuchev.kodaschool.recipeapp.mappers

import android.graphics.BitmapFactory
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.BitmapUtils.toByteArray
import com.stoyanvuchev.kodaschool.recipeapp.data.local.entity.RecipeEntity
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel

fun RecipeEntity.toRecipeModel() = RecipeModel(
    id = id,
    recipeId = recipeId,
    isBookmarked = isBookmarked,
    bookmarkTimestamp = bookmarkTimestamp,
    timestamp = timestamp,
    lastViewedTimestamp = lastViewedTimestamp,
    label = label,
    category = category,
    ingredients = ingredients,
    instructions = instructions,
    source = source,
    url = url,
    imageThumbnail = imageThumbnail,
    imageSmall = imageSmall,
    imageRegular = imageRegular,
    imageLarge = imageLarge,
    thumbnail = thumbnail?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
)

fun RecipeModel.toRecipeEntity() = RecipeEntity(
    id = id,
    recipeId = recipeId,
    isBookmarked = isBookmarked,
    bookmarkTimestamp = bookmarkTimestamp,
    timestamp = timestamp,
    lastViewedTimestamp = lastViewedTimestamp,
    label = label,
    category = category,
    ingredients = ingredients,
    instructions = instructions,
    source = source,
    url = url,
    imageThumbnail = imageThumbnail,
    imageSmall = imageSmall,
    imageRegular = imageRegular,
    imageLarge = imageLarge,
    thumbnail = thumbnail.toByteArray()
)