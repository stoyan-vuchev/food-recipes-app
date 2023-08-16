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

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens

/** The default property values of a [NavigationBar]. */
object NavigationBarDefaults {

    /** The height of the [NavigationBar] container (without the window insets). */
    val containerHeight: Dp get() = ComponentsTokens.NavigationBar.containerHeight

    /**
     * Returns the default colors of a [NavigationBar] with an optional overriding.
     * @param backgroundColor The background color of the navigation bar.
     * @param selectedItemIconBackgroundColor The icon background color of a navigation bar item.
     * @param selectedItemIconColor The color of the icon on a selected navigation bar item.
     * @param selectedItemLabelColor The color of the selected navigation bar item.
     * @param unselectedItemColor The color of the unselected navigation bar items.
     **/
    @Composable
    fun colors(
        backgroundColor: Color = ComponentsTokens.NavigationBar.backgroundColor,
        selectedItemIconBackgroundColor: Color = ComponentsTokens.NavigationBar.selectedItemIconBackgroundColor,
        selectedItemIconColor: Color = ComponentsTokens.NavigationBar.selectedItemIconColor,
        selectedItemLabelColor: Color = ComponentsTokens.NavigationBar.selectedItemLabelColor,
        unselectedItemColor: Color = ComponentsTokens.NavigationBar.unselectedItemColor
    ) = NavigationBarColors(
        backgroundColor = backgroundColor,
        selectedItemIconBackgroundColor = selectedItemIconBackgroundColor,
        selectedItemIconColor = selectedItemIconColor,
        selectedItemLabelColor = selectedItemLabelColor,
        unselectedItemColor = unselectedItemColor
    )

    /** The text style of a [NavigationBarItem] label. */
    val itemLabelTextStyle: TextStyle
        @Composable get() = ComponentsTokens.NavigationBar.itemLabelTextStyle

    /** The horizontal spacing of the [NavigationBar] container. */
    val containerHorizontalSpacing: Dp
        @Composable get() = ComponentsTokens.NavigationBar.containerHorizontalSpacing

    /** The window insets used for preventing the [NavigationBar] falling under the system bars. */
    val windowInsets: WindowInsets
        @Composable get() = ComponentsTokens.NavigationBar.windowInsets

}