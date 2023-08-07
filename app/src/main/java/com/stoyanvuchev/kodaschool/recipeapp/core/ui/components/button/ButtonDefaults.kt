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