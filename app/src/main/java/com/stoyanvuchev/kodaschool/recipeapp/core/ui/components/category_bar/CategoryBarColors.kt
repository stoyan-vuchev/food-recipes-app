package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * A custom colors set for styling the Category bar based on the design system and the Material3 skeleton.
 * @param selectedItemBackgroundColor The background color of the selected category item.
 * @param selectedItemContentColor The content color of the selected category item.
 * @param unselectedItemBackgroundColor The background color of the unselected category item/s.
 * @param unselectedItemContentColor The content color of the unselected category item/s.
 **/
@Immutable
class CategoryBarColors(
    private val selectedItemBackgroundColor: Color,
    private val selectedItemContentColor: Color,
    private val unselectedItemBackgroundColor: Color,
    private val unselectedItemContentColor: Color
) {

    /**
     * Consumes the Category bar item background color efficiently without causing unnecessary recomposition.
     * @param selected Indicates whether the item is selected or not.
     **/
    @Composable
    internal fun backgroundColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(
            if (selected) selectedItemBackgroundColor else unselectedItemBackgroundColor
        )
    }

    /**
     * Consumes the Category bar item content color efficiently without causing unnecessary recomposition.
     * @param selected Indicates whether the item is selected or not.
     **/
    @Composable
    internal fun contentColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(
            if (selected) selectedItemContentColor else unselectedItemContentColor
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CategoryBarColors) return false
        if (selectedItemBackgroundColor != other.selectedItemBackgroundColor) return false
        if (selectedItemContentColor != other.selectedItemContentColor) return false
        if (unselectedItemBackgroundColor != other.unselectedItemBackgroundColor) return false
        if (unselectedItemContentColor != other.unselectedItemContentColor) return false
        return true
    }

    override fun hashCode(): Int {
        var result = selectedItemBackgroundColor.hashCode()
        result = 31 * result + selectedItemContentColor.hashCode()
        result = 31 * result + unselectedItemBackgroundColor.hashCode()
        result = 31 * result + unselectedItemContentColor.hashCode()
        return result
    }

    override fun toString(): String {
        return "CategoryBarColors(" +
                "selectedItemBackgroundColor=$selectedItemBackgroundColor, " +
                "selectedItemContentColor=$selectedItemContentColor, " +
                "unselectedItemBackgroundColor=$unselectedItemBackgroundColor, " +
                "unselectedItemContentColor=$unselectedItemContentColor" +
                ")"
    }

}

/** CompositionLocal key used to pass [CategoryBarColors] down the composition for consumption. */
val LocalCategoryBarColors = staticCompositionLocalOf<CategoryBarColors> {
    error("CompositionLocal LocalCategoryBarColors not provided.")
}