package com.stoyanvuchev.kodaschool.recipeapp.mappers

import com.stoyanvuchev.kodaschool.recipeapp.data.local.entity.RecipeEntity
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel

fun RecipeEntity.toRecipeModel() = RecipeModel(
    recipeId = recipeId,
    isBookmarked = isBookmarked,
    bookmarkTimestamp = bookmarkTimestamp,
    label = label,
    category = category,
    ingredients = ingredients,
    source = source,
    url = url,
    imageThumbnail = imageThumbnail,
    imageSmall = imageSmall,
    imageRegular = imageRegular,
    imageLarge = imageLarge
)

fun RecipeModel.toRecipeEntity() = RecipeEntity(
    recipeId = recipeId,
    isBookmarked = isBookmarked,
    bookmarkTimestamp = bookmarkTimestamp,
    label = label,
    category = category,
    ingredients = ingredients,
    source = source,
    url = url,
    imageThumbnail = imageThumbnail,
    imageSmall = imageSmall,
    imageRegular = imageRegular,
    imageLarge = imageLarge
)