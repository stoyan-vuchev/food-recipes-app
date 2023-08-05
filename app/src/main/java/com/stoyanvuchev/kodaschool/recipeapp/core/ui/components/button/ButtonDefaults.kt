package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens

/** The default value properties of a Button. */
object ButtonDefaults {

    /**
     * Returns the default Button colors with an optional overriding.
     * @param backgroundColor The background color of the Button.
     * @param contentColor The content color of the Button.
     * @param disabledBackgroundColor The background color of the Button when disabled.
     * @param disabledContentColor The content color of the Button when disabled.
     **/
    @Composable
    fun colors(
        backgroundColor: Color = ComponentsTokens.ButtonBackgroundColor,
        contentColor: Color = ComponentsTokens.ButtonContentColor,
        disabledBackgroundColor: Color = ComponentsTokens.ButtonDisabledBackgroundColor,
        disabledContentColor: Color = ComponentsTokens.ButtonDisabledContentColor,
    ) = ButtonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledContentColor = disabledContentColor
    )

    /** The default Button shape. */
    val shape: Shape @Composable get() = ComponentsTokens.ButtonShape

    /** The default Button text style. */
    val textStyle: TextStyle @Composable get() = ComponentsTokens.ButtonTextStyle

    /** Horizontal padding for the Button content. */
    val horizontalPadding: Dp get() = ComponentsTokens.ButtonHorizontalPadding

    /** Vertical padding for the Button content. */
    val verticalPadding: Dp get() = ComponentsTokens.ButtonVerticalPadding

    /** The default minimum size of the Button. */
    val defaultMinSize: DpSize get() = ComponentsTokens.ButtonDefaultMinSize

}