package com.stoyanvuchev.kodaschool.recipeapp.domain.util

object RecipeIDExtractor {

    private const val RECIPE_ID_PATTERN = "#(recipe_[a-zA-Z0-9]+)"

    /** Extracts the Recipe ID from a Recipe Uri. */
    fun extractId(uri: String): String {
        return RECIPE_ID_PATTERN.toRegex().find(uri)?.groups?.get(1)?.value ?: ""
    }

}