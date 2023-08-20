/*
*
*   MIT License
*
*   Copyright (c) 2023 Stoyan Vuchev
*
*   Permission is hereby granted, free of charge, to any person obtaining a copy
*   of this software and associated documentation files (the "Software"), to deal
*   in the Software without restriction, including without limitation the rights
*   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*   copies of the Software, and to permit persons to whom the Software is
*   furnished to do so, subject to the following conditions:
*
*   The above copyright notice and this permission notice shall be included in all
*   copies or substantial portions of the Software.
*
*   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*   SOFTWARE.
*
*/

package com.stoyanvuchev.kodaschool.recipeapp.presentation

import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.UiString

/** Contains all main screen destinations. */
object MainScreenDestinations {

    /** Represents the Home screen destination. */
    data object Home : ScreenDestination(
        route = "main_home_route",
        label = UiString.FromResource(resId = R.string.home_screen_label),
        icon = R.drawable.home_icon
    )

    /** Represents the Search screen destination. */
    data object Search : ScreenDestination(
        route = "main_search_route",
        label = UiString.FromResource(resId = R.string.search_screen_label),
        icon = R.drawable.search_icon
    )

    /** Represents the screen for Saved / Bookmarked screen destination.  */
    data object Saved : ScreenDestination(
        route = "main_saved_route",
        label = UiString.FromResource(resId = R.string.saved_screen_label),
        icon = R.drawable.saved_icon
    )

    /**
     * Represents Recipe screen destination with a unique ID.
     * - Remember to replace {recipeId} with the actual ID when navigating.
     **/
    data object Recipe : ScreenDestination(route = "main_recipe_route_{recipeId}")

    /** Represents the screen for Recently Viewed recipes. */
    data object RecentlyViewed : ScreenDestination(route = "main_recently_viewed_route")

    /**
     * The route of the [MainScreenDestinations] navigation graph.
     * - Use [MainScreenDestinations.route] instead of [MainScreenDestinations.Home.route]
     * when defining the NavHost startDestination route.
     **/
    val route: String get() = "main_screen_destinations_route"

    /** The navigation destinations list for the navigation bar. */
    val navigationBarDestinations: List<ScreenDestination> = listOf(Home, Search, Saved)

}