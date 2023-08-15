package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.search_bar

/** Modes for defining a [SearchBar] behavior. */
enum class SearchBarMode {

    /**
     * This mode is used for scenario when the [SearchBar] is placed in a screen
     * that is not intended to handle searching, therefore,
     * it should handle a click event instead, and take the user to a screen
     * with a [SearchBar] configured in [SearchBarMode.Search].
     **/
    Navigate,

    /**
     * This mode is used when a [SearchBar] is placed in a screen
     * capable of handling search capabilities.
     **/
    Search

}