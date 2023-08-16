package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.navigation_rail

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens

/** The default property values of a [NavigationRail]. */
object NavigationRailDefaults {

    /**
     * Returns the default colors for a [NavigationRail] with an optional overriding.
     * @param backgroundColor The background color of the navigation rail.
     * @param selectedItemIconBackgroundColor The icon background color of a navigation rail item.
     * @param selectedItemIconColor The color of the icon on a selected navigation rail item.
     * @param selectedItemLabelColor The color of the selected navigation rail item.
     * @param unselectedItemColor The color of the unselected navigation rail items.
     **/
    @Composable
    fun colors(
        backgroundColor: Color = ComponentsTokens.NavigationRail.backgroundColor,
        selectedItemIconBackgroundColor: Color = ComponentsTokens.NavigationRail.selectedItemIconBackgroundColor,
        selectedItemIconColor: Color = ComponentsTokens.NavigationRail.selectedItemIconColor,
        selectedItemLabelColor: Color = ComponentsTokens.NavigationRail.selectedItemLabelColor,
        unselectedItemColor: Color = ComponentsTokens.NavigationRail.unselectedItemColor
    ) = NavigationRailColors(
        backgroundColor = backgroundColor,
        selectedItemIconBackgroundColor = selectedItemIconBackgroundColor,
        selectedItemIconColor = selectedItemIconColor,
        selectedItemLabelColor = selectedItemLabelColor,
        unselectedItemColor = unselectedItemColor
    )

    /** The default [TextStyle] of a [NavigationRailItem] label. */
    val itemsLabelTextStyle: TextStyle
        @Composable get() = ComponentsTokens.NavigationRail.itemLabelTextStyle

    /** The default [WindowInsets] values of a [NavigationRail]. */
    val windowInsets: WindowInsets
        @Composable get() = ComponentsTokens.NavigationRail.windowInsets

}