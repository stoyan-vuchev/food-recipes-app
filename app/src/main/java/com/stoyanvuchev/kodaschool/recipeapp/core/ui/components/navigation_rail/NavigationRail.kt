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

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button.Button
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button.ButtonDefaults
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme

/**
 * A custom navigation rail component displaying [NavigationRailItem]s for each navigation destination.
 * @param modifier Used for further customization.
 * @param header Displaying an optional header above the [items] containing action components.
 * @param colors Used for applying color to the navigation rail and its items.
 * @param itemsLabelTextStyle Used for styling the [NavigationRailItem] label [TextStyle].
 * @param itemsAlignment Used for aligning the the [NavigationRailItem]s vertically.
 * @param windowInsets Consuming [WindowInsets] to prevent an overlap with the system bars.
 * @param items Displaying [NavigationRailItem]s for each navigation destination.
 **/
@Composable
fun NavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    colors: NavigationRailColors = NavigationRailDefaults.colors(),
    itemsLabelTextStyle: TextStyle = NavigationRailDefaults.itemsLabelTextStyle,
    itemsAlignment: Alignment.Vertical = Alignment.CenterVertically,
    windowInsets: WindowInsets = NavigationRailDefaults.windowInsets,
    items: @Composable NavigationRailScope.() -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = colors.backgroundColor)
            .windowInsetsPadding(insets = windowInsets)
            .then(modifier)
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(ComponentsTokens.NavigationRail.containerWidth)
                .padding(vertical = ComponentsTokens.NavigationRail.contentSpacing)
        ) {

            header?.let { headerContent ->

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = headerContent
                )

                Spacer(modifier = Modifier.height(ComponentsTokens.NavigationRail.headerSpacing))

            }

            CompositionLocalProvider(
                LocalNavigationRailColors provides colors,
                LocalTextStyle provides itemsLabelTextStyle,
                content = {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                            space = ComponentsTokens.NavigationRail.contentSpacing,
                            alignment = itemsAlignment
                        ),
                        content = items
                    )

                }
            )

        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(ComponentsTokens.Separator.thickness)
                .background(color = ComponentsTokens.Separator.color)
        )

    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = Devices.FOLDABLE)
@Composable
private fun NavigationRailPreviewLight() {
    FoodRecipesTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            NavigationRail(
                header = {

                    Button(
                        modifier = Modifier.size(56.dp),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.colors(
                            backgroundColor = Theme.colors.accentContainer,
                            contentColor = Theme.colors.onAccentContainer
                        )
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.saved_icon),
                            contentDescription = null
                        )

                    }

                },
                items = {

                    NavigationRailItem(
                        selected = true,
                        onSelected = {},
                        icon = painterResource(id = R.drawable.home_icon),
                        label = "Home"
                    )

                    NavigationRailItem(
                        selected = false,
                        onSelected = {},
                        icon = painterResource(id = R.drawable.search_icon),
                        label = "Search"
                    )

                    NavigationRailItem(
                        selected = false,
                        onSelected = {},
                        icon = painterResource(id = R.drawable.saved_icon),
                        label = "Saved"
                    )

                }
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, device = Devices.FOLDABLE)
@Composable
private fun NavigationRailPreviewDark() {
    FoodRecipesTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            NavigationRail(
                header = {

                    Button(
                        modifier = Modifier.size(56.dp),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.colors(
                            backgroundColor = Theme.colors.accentContainer,
                            contentColor = Theme.colors.onAccentContainer
                        )
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.saved_icon),
                            contentDescription = null
                        )

                    }

                },
                items = {

                    NavigationRailItem(
                        selected = true,
                        onSelected = {},
                        icon = painterResource(id = R.drawable.home_icon),
                        label = "Home"
                    )

                    NavigationRailItem(
                        selected = false,
                        onSelected = {},
                        icon = painterResource(id = R.drawable.search_icon),
                        label = "Search"
                    )

                    NavigationRailItem(
                        selected = false,
                        onSelected = {},
                        icon = painterResource(id = R.drawable.saved_icon),
                        label = "Saved"
                    )

                }
            )
        }
    }
}

/** Using [NavigationRailScope] as a typealias of [ColumnScope] to avoid confusion. */
typealias NavigationRailScope = ColumnScope