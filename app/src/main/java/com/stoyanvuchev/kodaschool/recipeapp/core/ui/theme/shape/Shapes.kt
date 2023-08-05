package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.shape

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

/** A custom shapes based on the design system and the Material3 skeleton. */
@Immutable
class Shapes(
    val small: Shape = ShapeTokens.SmallShape,
    val medium: Shape = ShapeTokens.MediumShape,
    val large: Shape = ShapeTokens.LargeShape,
    val extraLarge: Shape = ShapeTokens.ExtraLargeShape
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Shapes) return false
        if (small != other.small) return false
        if (medium != other.medium) return false
        if (large != other.large) return false
        if (extraLarge != other.extraLarge) return false
        return true
    }

    override fun hashCode(): Int {
        var result = small.hashCode()
        result = 31 * result + medium.hashCode()
        result = 31 * result + large.hashCode()
        result = 31 * result + extraLarge.hashCode()
        return result
    }

    override fun toString(): String {
        return "Shapes(" +
                "small=$small, " +
                "medium=$medium, " +
                "large=$large, " +
                "extraLarge=$extraLarge" +
                ")"
    }

}

/** CompositionLocal key used for passing [Shapes] down the composition for consumption. */
val LocalShapes = staticCompositionLocalOf { Shapes() }