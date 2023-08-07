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