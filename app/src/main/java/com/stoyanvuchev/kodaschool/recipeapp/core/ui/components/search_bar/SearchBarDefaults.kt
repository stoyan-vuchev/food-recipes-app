package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.search_bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens

/** The default property values of a [SearchBar]. */
object SearchBarDefaults {

    /** The default outline border width of a [SearchBar]. */
    val borderWidth: Dp get() = ComponentsTokens.SearchBar.borderWidth

    /**
     * Returns the default colors of a [SearchBar] with an optional overriding.
     * @param borderColor The color of the outline border.
     * @param contentColor The color of the content.
     **/
    @Composable
    fun colors(
        borderColor: Color = ComponentsTokens.SearchBar.borderColor,
        contentColor: Color = ComponentsTokens.SearchBar.contentColor
    ) = SearchBarColors(
        borderColor = borderColor,
        contentColor = contentColor
    )

    /** The default text style of a [SearchBar]. */
    val textStyle: TextStyle @Composable get() = ComponentsTokens.SearchBar.textStyle

    /** The default shape of a [SearchBar]. */
    val shape: Shape @Composable get() = ComponentsTokens.SearchBar.shape

}