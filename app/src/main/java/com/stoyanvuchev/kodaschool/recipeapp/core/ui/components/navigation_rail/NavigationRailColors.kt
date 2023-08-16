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

package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.navigation_rail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * A custom colors set for styling a [NavigationRail] based on the design system and the Material3 skeleton.
 * @param backgroundColor The background color of the navigation rail.
 * @param selectedItemIconBackgroundColor The icon background color of a navigation rail item.
 * @param selectedItemIconColor The color of the icon on a selected navigation rail item.
 * @param selectedItemLabelColor The color of the selected navigation rail item.
 * @param unselectedItemColor The color of the unselected navigation rail items.
 **/
@Immutable
class NavigationRailColors(
    val backgroundColor: Color,
    private val selectedItemIconBackgroundColor: Color,
    private val selectedItemIconColor: Color,
    private val selectedItemLabelColor: Color,
    private val unselectedItemColor: Color
) {

    /**
     * Consumes the icon color of a [NavigationRailItem] as state, depending on whether it's selected or not.
     * @param selected Indicates whether the item is selected or not.
     **/
    @Composable
    internal fun iconColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(
            if (selected) selectedItemIconColor else unselectedItemColor
        )
    }

    /**
     * Consumes the icon background color of a [NavigationRailItem] as state, depending on whether it's selected or not.
     * @param selected Indicates whether the item is selected or not.
     **/
    @Composable
    internal fun iconBackgroundColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(
            selectedItemIconBackgroundColor.copy(alpha = if (selected) 1f else 0f)
        )
    }

    /**
     * Consumes the label color of a [NavigationRailItem] as state, depending on whether it's selected or not.
     * @param selected Indicates whether the item is selected or not.
     **/
    @Composable
    internal fun labelColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(
            if (selected) selectedItemLabelColor else unselectedItemColor
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NavigationRailColors) return false
        if (backgroundColor != other.backgroundColor) return false
        if (selectedItemIconBackgroundColor != other.selectedItemIconBackgroundColor) return false
        if (selectedItemIconColor != other.selectedItemIconColor) return false
        if (selectedItemLabelColor != other.selectedItemLabelColor) return false
        if (unselectedItemColor != other.unselectedItemColor) return false
        return true
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + selectedItemIconBackgroundColor.hashCode()
        result = 31 * result + selectedItemIconColor.hashCode()
        result = 31 * result + selectedItemLabelColor.hashCode()
        result = 31 * result + unselectedItemColor.hashCode()
        return result
    }

    override fun toString(): String {
        return "NavigationRailColors(" +
                "backgroundColor=$backgroundColor, " +
                "selectedItemIconBackgroundColor=$selectedItemIconBackgroundColor, " +
                "selectedItemIconColor=$selectedItemIconColor, " +
                "selectedItemLabelColor=$selectedItemLabelColor, " +
                "unselectedItemColor=$unselectedItemColor" +
                ")"
    }

}

/** CompositionLocal key used for passing [NavigationRailColors] down the composition for consumption. */
val LocalNavigationRailColors = staticCompositionLocalOf<NavigationRailColors> {
    error("CompositionLocal LocalNavigationRailColors is not provided.")
}