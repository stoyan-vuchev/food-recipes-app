package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.navigation_bar

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle

/**
 * A navigation bar component displaying [NavigationBarItem]s for each navigation destination.
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
            .testTag("navigation_bar")
            .then(modifier)
            .then(Modifier.windowInsetsPadding(windowInsets)),
        content = {

            Divider(color = colors.separatorColor)

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