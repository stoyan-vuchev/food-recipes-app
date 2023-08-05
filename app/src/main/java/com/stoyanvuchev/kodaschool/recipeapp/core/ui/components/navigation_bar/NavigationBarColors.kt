package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.navigation_bar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * A custom colors set for styling the Navigation bar based on the design system and the Material3 skeleton.
 * @param backgroundColor The background color of the navigation bar.
 * @param selectedItemColor The color of the selected navigation bar item.
 * @param unselectedItemColor The color of the unselected navigation bar items.
 **/
@Immutable
class NavigationBarColors(
    val backgroundColor: Color,
    private val selectedItemColor: Color,
    private val unselectedItemColor: Color
) {

    /**
     * Consumes the navigation bar item color efficiently without causing unnecessary recomposition.
     * @param selected Indicates whether the item is selected or not.
     **/
    @Composable
    internal fun itemColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(if (selected) selectedItemColor else unselectedItemColor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NavigationBarColors) return false
        if (backgroundColor != other.backgroundColor) return false
        if (selectedItemColor != other.selectedItemColor) return false
        if (unselectedItemColor != other.unselectedItemColor) return false
        return true
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + selectedItemColor.hashCode()
        result = 31 * result + unselectedItemColor.hashCode()
        return result
    }

    override fun toString(): String {
        return "NavigationBarColors(" +
                "backgroundColor=$backgroundColor, " +
                "selectedItemColor=$selectedItemColor, " +
                "unselectedItemColor=$unselectedItemColor" +
                ")"
    }

}

/** CompositionLocal key used for passing [NavigationBarColors] down the composition for consumption. */
val LocalNavigationBarColors = staticCompositionLocalOf<NavigationBarColors> {
    error("CompositionLocal LocalNavigationBarColors is not provided.")
}