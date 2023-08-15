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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import sv.lib.squircleshape.CornerSmoothing
import sv.lib.squircleshape.SquircleShape

/** Default shape tokens used by the design system. */
@Immutable
object ShapeTokens {

    val SmallShapeRadius: Dp get() = 12.dp
    val MediumShapeRadius: Dp get() = 16.dp
    val LargeShapeRadius: Dp get() = 20.dp
    val ExtraLargeShapeRadius: Dp get() = 32.dp

    val SmallShape: Shape get() = SquircleShape(radius = SmallShapeRadius)
    val MediumShape: Shape get() = SquircleShape(radius = MediumShapeRadius)
    val LargeShape: Shape get() = SquircleShape(radius = LargeShapeRadius)
    val ExtraLargeShape: Shape
        get() = SquircleShape(
            radius = ExtraLargeShapeRadius,
            cornerSmoothing = CornerSmoothing.Small
        )

}