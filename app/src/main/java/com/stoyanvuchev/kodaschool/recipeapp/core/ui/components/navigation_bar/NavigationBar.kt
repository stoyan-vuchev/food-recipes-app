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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens

/**
 * A custom navigation bar component displaying [NavigationBarItem]s for each navigation destination.
 * @param modifier Used for further customization.
 * @param colors Used for styling the navigation bar colors.
 * @param itemLabelTextStyle Used for styling the [NavigationBarItem] label [TextStyle].
 * @param windowInsets Consuming [WindowInsets] to prevent an overlap with the system bars.
 * @param items Displaying [NavigationBarItem]s for each navigation destination.
 **/
@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    colors: NavigationBarColors = NavigationBarDefaults.colors(),
    itemLabelTextStyle: TextStyle = NavigationBarDefaults.itemLabelTextStyle,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    items: @Composable NavigationBarScope.() -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colors.backgroundColor)
            .windowInsetsPadding(windowInsets)
            .then(modifier),
        content = {

            Divider(
                color = ComponentsTokens.Separator.color,
                thickness = ComponentsTokens.Separator.thickness
            )

            CompositionLocalProvider(
                LocalNavigationBarColors provides colors,
                LocalTextStyle provides itemLabelTextStyle,
                content = {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(NavigationBarDefaults.containerHeight),
                        horizontalArrangement = Arrangement.spacedBy(
                            NavigationBarDefaults.containerHorizontalSpacing
                        ),
                        content = items
                    )

                }
            )

        }
    )

}

/** Using [NavigationBarScope] as a typealias of [RowScope] to avoid confusion. */
typealias NavigationBarScope = RowScope