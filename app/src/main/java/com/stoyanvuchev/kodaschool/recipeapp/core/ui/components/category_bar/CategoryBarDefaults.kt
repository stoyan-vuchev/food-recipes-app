package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens

/** The default property values of a Category bar. */
object CategoryBarDefaults {

    /** The height of the Category bar container. */
    internal val containerHeight: Dp get() = ComponentsTokens.CategoryBarContainerHeight

    /** The height of the Category bar item container. */
    internal val itemContainerHeight: Dp get() = ComponentsTokens.CategoryBarItemContainerHeight

    /**
     * Returns the default colors of a Category bar with an optional overriding.
     * @param selectedItemBackgroundColor The background color of the selected category item.
     * @param selectedItemContentColor The content color of the selected category item.
     * @param unselectedItemBackgroundColor The background color of the unselected category item/s.
     * @param unselectedItemContentColor The content color of the unselected category item/s.
     **/
    @Composable
    fun colors(
        selectedItemBackgroundColor: Color = ComponentsTokens.CategoryBarSelectedItemBackgroundColor,
        selectedItemContentColor: Color = ComponentsTokens.CategoryBarSelectedItemContentColor,
        unselectedItemBackgroundColor: Color = ComponentsTokens.CategoryBarUnselectedItemBackgroundColor,
        unselectedItemContentColor: Color = ComponentsTokens.CategoryBarUnselectedItemContentColor
    ) = CategoryBarColors(
        selectedItemBackgroundColor = selectedItemBackgroundColor,
        selectedItemContentColor = selectedItemContentColor,
        unselectedItemBackgroundColor = unselectedItemBackgroundColor,
        unselectedItemContentColor = unselectedItemContentColor
    )

    /** The horizontal spacing of the Category bar container. */
    internal val containerHorizontalSpacing: Dp
        @Composable get() = ComponentsTokens.CategoryBarContainerHorizontalSpacing

    /** The horizontal padding of the Category bar item container. */
    internal val itemContainerHorizontalPadding: Dp
        @Composable get() = ComponentsTokens.CategoryBarItemContainerHorizontalPadding

    /** The texts style of the Category bar item. */
    val itemTextStyle: TextStyle
        @Composable get() = ComponentsTokens.CategoryBarItemTextStyle

    /** The shape of the Category bar item. */
    val itemShape: Shape
        @Composable get() = ComponentsTokens.CategoryBarItemShape

    /** CompositionLocal key used for passing the [itemShape] to the Category bar items for consumption. */
    internal val LocalCategoryItemShape = staticCompositionLocalOf<Shape> {
        error("CompositionLocal LocalCategoryItemShape not provided.")
    }

}