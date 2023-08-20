package com.stoyanvuchev.kodaschool.recipeapp.mappers

import com.stoyanvuchev.kodaschool.recipeapp.core.utils.BitmapUtils
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.response.RecipeResponse
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory.Companion.toRecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel
import com.stoyanvuchev.kodaschool.recipeapp.domain.util.RecipeIDExtractor

fun RecipeResponse.toRecipeModel(timestamp: Long) = RecipeModel(
    recipeId = RecipeIDExtractor.extractId(uri = uri),
    isBookmarked = false,
    bookmarkTimestamp = null,
    timestamp = timestamp,
    lastViewedTimestamp = null,
    label = label,
    category = this.category.firstOrNull()?.toRecipesCategory() ?: RecipesCategory.Default,
    ingredients = ingredients?.requireNoNulls()?.distinct() ?: emptyList(),
    instructions = instructions?.requireNoNulls()?.distinct() ?: emptyList(),
    source = source,
    url = url,
    imageThumbnail = images.thumbnail?.url ?: "",
    imageSmall = images.small?.url ?: "",
    imageRegular = images.regular?.url ?: "",
    imageLarge = images.large?.url ?: "",
    thumbnail = BitmapUtils.getBitmapFromImageUrl(images.small?.url)
)