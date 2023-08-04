package com.stoyanvuchev.kodaschool.recipeapp.domain

enum class RecipesCategory(val stringValue: String) {

    Breakfast("breakfast"),
    Lunch("lunch"),
    Dinner("dinner"),
    Teatime("teatime"),
    Snack("snack");

    companion object {

        val Default = Breakfast

        fun String.toRecipesCategory() = when (this) {
            Breakfast.stringValue -> Breakfast
            Lunch.stringValue -> Lunch
            Dinner.stringValue -> Dinner
            Teatime.stringValue -> Teatime
            Snack.stringValue -> Snack
            else -> Default
        }

    }

}