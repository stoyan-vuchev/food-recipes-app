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

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow

/**
 * An item for a [NavigationBar].
 * @param selected Whether the item is selected or not.
 * @param onSelected Callback invoked when the item is being selected.
 * @param icon The item icon painter.
 * @param label The item label text.
 **/
@Composable
fun NavigationBarScope.NavigationBarItem(
    selected: Boolean,
    onSelected: () -> Unit,
    icon: Painter,
    label: String
) {

    val color by animateColorAsState(
        targetValue = LocalNavigationBarColors.current.itemColor(selected).value,
        label = ""
    )

    CompositionLocalProvider(
        LocalContentColor provides color,
        content = {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(
                        enabled = !selected,
                        onClick = onSelected
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {

                    Icon(
                        modifier = Modifier.size(NavigationBarDefaults.itemIconSize),
                        painter = icon,
                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier.height(
                            NavigationBarDefaults.itemContainerVerticalSpacing
                        )
                    )

                    Text(
                        modifier = Modifier.padding(
                            horizontal = NavigationBarDefaults.containerHorizontalSpacing
                        ),
                        text = label,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            )

        }
    )

}