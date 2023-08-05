package com.stoyanvuchev.kodaschool.recipeapp.presentation

import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.UiString

/** Contains all main screen destinations. */
object MainScreenDestinations {

    /** Represents the Home screen destination. */
    object Home : ScreenDestination(
        route = "main_home_route",
        label = UiString.FromResource(resId = R.string.home_screen_label),
        icon = R.drawable.home_icon
    )

    /** Represents the Search screen destination. */
    object Search : ScreenDestination(
        route = "main_search_route",
        label = UiString.FromResource(resId = R.string.search_screen_label),
        icon = R.drawable.search_icon
    )

    /** Represents the screen for Saved / Bookmarked screen destination.  */
    object Saved : ScreenDestination(
        route = "main_saved_route",
        label = UiString.FromResource(resId = R.string.saved_screen_label),
        icon = R.drawable.saved_icon
    )

    /**
     * Represents Recipe screen destination with a unique ID.
     * - Remember to replace {recipeId} with the actual ID when navigating.
     **/
    object Recipe : ScreenDestination(route = "main_recipe_route_{recipeId}")

    /**
     * The route of the [MainScreenDestinations] navigation graph.
     * - Use [MainScreenDestinations.route] instead of [MainScreenDestinations.Home.route]
     * when defining the NavHost startDestination route.
     **/
    val route: String get() = "main_screen_destinations_route"

    /** The navigation destinations list for the navigation bar. */
    val navigationBarDestinations: List<ScreenDestination> = listOf(Home, Search, Saved)

}