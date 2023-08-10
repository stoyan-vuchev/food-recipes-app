/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    internal val containerHeight: Dp get() = ComponentsTokens.CategoryBar.containerHeight

    /** The height of the Category bar item container. */
    internal val itemContainerHeight: Dp get() = ComponentsTokens.CategoryBar.itemContainerHeight

    /**
     * Returns the default colors of a Category bar with an optional overriding.
     * @param selectedItemBackgroundColor The background color of the selected category item.
     * @param selectedItemContentColor The content color of the selected category item.
     * @param unselectedItemBackgroundColor The background color of the unselected category item/s.
     * @param unselectedItemContentColor The content color of the unselected category item/s.
     **/
    @Composable
    fun colors(
        selectedItemBackgroundColor: Color = ComponentsTokens.CategoryBar.selectedItemBackgroundColor,
        selectedItemContentColor: Color = ComponentsTokens.CategoryBar.selectedItemContentColor,
        unselectedItemBackgroundColor: Color = ComponentsTokens.CategoryBar.unselectedItemBackgroundColor,
        unselectedItemContentColor: Color = ComponentsTokens.CategoryBar.unselectedItemContentColor
    ) = CategoryBarColors(
        selectedItemBackgroundColor = selectedItemBackgroundColor,
        selectedItemContentColor = selectedItemContentColor,
        unselectedItemBackgroundColor = unselectedItemBackgroundColor,
        unselectedItemContentColor = unselectedItemContentColor
    )

    /** The horizontal spacing of the Category bar container. */
    internal val containerHorizontalSpacing: Dp
        @Composable get() = ComponentsTokens.CategoryBar.containerHorizontalSpacing

    /** The horizontal padding of the Category bar item container. */
    internal val itemContainerHorizontalPadding: Dp
        @Composable get() = ComponentsTokens.CategoryBar.itemContainerHorizontalPadding

    /** The texts style of the Category bar item. */
    val itemTextStyle: TextStyle
        @Composable get() = ComponentsTokens.CategoryBar.itemTextStyle

    /** The shape of the Category bar item. */
    val itemShape: Shape
        @Composable get() = ComponentsTokens.CategoryBar.itemShape

    /** CompositionLocal key used for passing the [itemShape] to the Category bar items for consumption. */
    internal val LocalCategoryItemShape = staticCompositionLocalOf<Shape> {
        error("CompositionLocal LocalCategoryItemShape not provided.")
    }

}