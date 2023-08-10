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