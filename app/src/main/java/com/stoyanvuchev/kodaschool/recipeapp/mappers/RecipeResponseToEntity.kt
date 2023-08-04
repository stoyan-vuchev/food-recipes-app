package com.stoyanvuchev.kodaschool.recipeapp.mappers

import com.stoyanvuchev.kodaschool.recipeapp.data.local.entity.RecipeEntity
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.response.RecipeResponse
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory.Companion.Default
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory.Companion.toRecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.util.RecipeIDExtractor

fun RecipeResponse.toRecipeEntity() = RecipeEntity(
    recipeId = RecipeIDExtractor.extractId(uri = uri),
    isBookmarked = false,
    bookmarkTimestamp = null,
    label = label,
    category = category.firstOrNull()?.toRecipesCategory() ?: Default,
    ingredients = ingredients,
    source = source,
    url = url,
    imageThumbnail = images.thumbnail.url,
    imageSmall = images.small.url,
    imageRegular = images.regular.url,
    imageLarge = images.large.url
)