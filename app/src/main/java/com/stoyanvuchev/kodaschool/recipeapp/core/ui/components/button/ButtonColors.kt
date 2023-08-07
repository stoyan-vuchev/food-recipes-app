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
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

/**
 * A custom colors set for a Button based on the design system and the Material3 skeleton.
 * @param backgroundColor The background color of the Button.
 * @param contentColor The content color of the Button.
 * @param disabledBackgroundColor The background color of the Button when disabled.
 * @param disabledContentColor The content color of the Button when disabled.
 **/
@Immutable
class ButtonColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color,
    private val disabledContentColor: Color
) {

    /**
     * Consuming the [backgroundColor] efficiently without causing unnecessary recomposition.
     * @param enabled Indicates whether the Button is enabled or not.
     **/
    @Composable
    fun background(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
    }

    /**
     * Consuming the [contentColor] efficiently without causing unnecessary recomposition.
     * @param enabled Indicates whether the Button is enabled or not.
     **/
    @Composable
    fun content(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ButtonColors) return false
        if (backgroundColor != other.backgroundColor) return false
        if (contentColor != other.contentColor) return false
        if (disabledBackgroundColor != other.disabledBackgroundColor) return false
        if (disabledContentColor != other.disabledContentColor) return false
        return true
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + disabledBackgroundColor.hashCode()
        result = 31 * result + disabledContentColor.hashCode()
        return result
    }

    override fun toString(): String {
        return "ButtonColors(" +
                "backgroundColor=$backgroundColor, " +
                "contentColor=$contentColor, " +
                "disabledBackgroundColor=$disabledBackgroundColor, " +
                "disabledContentColor=$disabledContentColor" +
                ")"
    }

}