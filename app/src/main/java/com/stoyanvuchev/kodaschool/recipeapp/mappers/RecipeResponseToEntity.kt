package com.stoyanvuchev.kodaschool.recipeapp.mappers

import com.stoyanvuchev.kodaschool.recipeapp.core.utils.BitmapUtils
import com.stoyanvuchev.kodaschool.recipeapp.data.local.entity.RecipeEntity
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.response.RecipeResponse
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory.Companion.Default
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory.Companion.toRecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.util.RecipeIDExtractor

fun RecipeResponse.toRecipeEntity(timestamp: Long) = RecipeEntity(
    recipeId = RecipeIDExtractor.extractId(uri = uri),
    isBookmarked = false,
    bookmarkTimestamp = null,
    timestamp = timestamp,
    lastViewedTimestamp = null,
    label = label,
    category = this.category.firstOrNull()?.toRecipesCategory() ?: Default,
    ingredients = ingredients?.requireNoNulls()?.distinct() ?: emptyList(),
    instructions = instructions?.requireNoNulls()?.distinct() ?: emptyList(),
    source = source,
    url = url,
    thumbnail = BitmapUtils.getByteArrayFromImageUrl(images.small?.url),
    image = BitmapUtils.getByteArrayFromImageUrl(images.regular?.url)
)

fun RecipeResponse.toRecipeEntity(
    timestamp: Long,
    category: RecipesCategory
) = RecipeEntity(
    recipeId = RecipeIDExtractor.extractId(uri = uri),
    isBookmarked = false,
    bookmarkTimestamp = null,
    timestamp = timestamp,
    lastViewedTimestamp = null,
    label = label,
    category = category,
    ingredients = ingredients?.requireNoNulls()?.distinct() ?: emptyList(),
    instructions = instructions?.requireNoNulls()?.distinct() ?: emptyList(),
    source = source,
    url = url,
    thumbnail = BitmapUtils.getByteArrayFromImageUrl(images.small?.url),
    image = BitmapUtils.getByteArrayFromImageUrl(images.regular?.url)
)