package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.UiString

/**
 * An abstraction used to define a screen destination.
 * @param route The route of the screen destination.
 * @param label A label to be displayed in a navigation bar item. (optional)
 * @param icon An icon to be displayed in a navigation bar item. (optional)
 **/
@Immutable
abstract class ScreenDestination(
    val route: String,
    val label: UiString? = null,
    @DrawableRes val icon: Int? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ScreenDestination) return false
        if (route != other.route) return false
        if (label != other.label) return false
        if (icon != other.icon) return false
        return true
    }

    override fun hashCode(): Int {
        var result = route.hashCode()
        result = 31 * result + label.hashCode()
        result = 31 * result + icon.hashCode()
        return result
    }

    override fun toString(): String {
        return "ScreenDestination(route=$route, label=$label, selectedIcon=$icon)"
    }

}