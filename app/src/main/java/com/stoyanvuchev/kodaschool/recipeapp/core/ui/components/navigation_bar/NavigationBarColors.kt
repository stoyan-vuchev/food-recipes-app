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

package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.navigation_bar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * A custom colors set for styling the Navigation bar based on the design system and the Material3 skeleton.
 * @param backgroundColor The background color of the navigation bar.
 * @param selectedItemColor The color of the selected navigation bar item.
 * @param unselectedItemColor The color of the unselected navigation bar items.
 **/
@Immutable
class NavigationBarColors(
    val backgroundColor: Color,
    private val selectedItemColor: Color,
    private val unselectedItemColor: Color
) {

    /**
     * Consumes the navigation bar item color efficiently without causing unnecessary recomposition.
     * @param selected Indicates whether the item is selected or not.
     **/
    @Composable
    internal fun itemColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(if (selected) selectedItemColor else unselectedItemColor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NavigationBarColors) return false
        if (backgroundColor != other.backgroundColor) return false
        if (selectedItemColor != other.selectedItemColor) return false
        if (unselectedItemColor != other.unselectedItemColor) return false
        return true
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + selectedItemColor.hashCode()
        result = 31 * result + unselectedItemColor.hashCode()
        return result
    }

    override fun toString(): String {
        return "NavigationBarColors(" +
                "backgroundColor=$backgroundColor, " +
                "selectedItemColor=$selectedItemColor, " +
                "unselectedItemColor=$unselectedItemColor" +
                ")"
    }

}

/** CompositionLocal key used for passing [NavigationBarColors] down the composition for consumption. */
val LocalNavigationBarColors = staticCompositionLocalOf<NavigationBarColors> {
    error("CompositionLocal LocalNavigationBarColors is not provided.")
}