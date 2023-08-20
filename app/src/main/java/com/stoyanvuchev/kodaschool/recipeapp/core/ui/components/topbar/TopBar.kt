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

package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.lerp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.CategoryBar

/**
 * A custom top bar UI component based on the design system and the Material3 skeleton.
 * @param modifier Further customization.
 * @param title The title of the top bar.
 * @param navigationButton An optional navigation button (e.g. for navigating back or to open a menu).
 * @param scrollBehavior The top bar scroll behavior.
 * @param backgroundColor The background color of the top bar.
 * @param contentColor Applies content color to the [title] and [navigationButton].
 * @param windowInsets Applies window insets values to the top bar.
 **/
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationButton: @Composable (() -> Unit)? = null,
    scrollBehavior: TopBarScrollBehavior? = null,
    backgroundColor: Color = TopBarDefaults.backgroundColor,
    contentColor: Color = TopBarDefaults.contentColor,
    windowInsets: WindowInsets = TopBarDefaults.windowInsets
) = ResizableTopBarLayout(
    modifier = modifier,
    title = title,
    smallTitleTextStyle = ComponentsTokens.TopBar.smallTitleTextStyle,
    largeTitleTextStyle = ComponentsTokens.TopBar.largeTitleTextStyle,
    navigationButton = navigationButton,
    content = null,
    windowInsets = windowInsets,
    backgroundColor = backgroundColor,
    contentColor = contentColor,
    maxHeight = TopBarDefaults.largeContainerHeight,
    pinnedHeight = TopBarDefaults.smallContainerHeight,
    scrollBehavior = scrollBehavior
)

/**
 * A custom top bar UI component based on the design system and the Material3 skeleton.
 * @param modifier Further customization.
 * @param title The title of the top bar.
 * @param navigationButton An optional navigation button (e.g. for navigating back or to open a menu).
 * @param content An optional content to be displayed under the top bar. (e.g. a [CategoryBar]).
 * @param scrollBehavior The top bar scroll behavior.
 * @param backgroundColor The background color of the top bar.
 * @param contentColor Applies content color to the [title] and [navigationButton].
 * @param windowInsets Applies window insets values to the top bar.
 **/
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationButton: @Composable (() -> Unit)? = null,
    content: @Composable (ColumnScope.() -> Unit)? = null,
    scrollBehavior: TopBarScrollBehavior? = null,
    backgroundColor: Color = TopBarDefaults.backgroundColor,
    contentColor: Color = TopBarDefaults.contentColor,
    windowInsets: WindowInsets = TopBarDefaults.windowInsets
) = ResizableTopBarLayout(
    modifier = modifier,
    title = title,
    smallTitleTextStyle = ComponentsTokens.TopBar.smallTitleTextStyle,
    largeTitleTextStyle = ComponentsTokens.TopBar.largeTitleTextStyle,
    navigationButton = navigationButton,
    content = content,
    windowInsets = windowInsets,
    backgroundColor = backgroundColor,
    contentColor = contentColor,
    maxHeight = TopBarDefaults.largeContainerHeight,
    pinnedHeight = TopBarDefaults.smallContainerHeight,
    scrollBehavior = scrollBehavior
)

/**
 * Resizable layout for a [TopBar].
 * @param modifier Further customization.
 * @param title The title of the top bar.
 * @param smallTitleTextStyle The text style of the title in [pinnedHeight].
 * @param largeTitleTextStyle The text style of the title in [maxHeight].
 * @param navigationButton A navigation button (e.g. for navigating back or to open a menu).
 * @param windowInsets Applies window insets values to the top bar.
 * @param backgroundColor The background color of the top bar.
 * @param contentColor Applies content color to the [title] and [navigationButton].
 * @param maxHeight The maximum height of the top bar container (excluding [windowInsets]).
 * @param pinnedHeight The minimum height of the top bar container (excluding [windowInsets]).
 * @param scrollBehavior The top bar scroll behavior.
 **/
@Composable
private fun ResizableTopBarLayout(
    modifier: Modifier = Modifier,
    title: String,
    smallTitleTextStyle: TextStyle,
    largeTitleTextStyle: TextStyle,
    navigationButton: @Composable (() -> Unit)?,
    content: @Composable (ColumnScope.() -> Unit)?,
    windowInsets: WindowInsets,
    backgroundColor: Color,
    contentColor: Color,
    maxHeight: Dp,
    pinnedHeight: Dp,
    scrollBehavior: TopBarScrollBehavior?
) {

    LaunchedEffect(key1 = true) {
        if (maxHeight <= pinnedHeight) {
            throw IllegalArgumentException(
                "A ResizableTopBarLayout max height should be greater than its pinned height!"
            )
        }
    }

    val density = LocalDensity.current
    val statusBarHeightPx = with(density) { windowInsets.getTop(this).toFloat() }
    val pinnedHeightPx = with(density) { pinnedHeight.toPx() + statusBarHeightPx }
    val maxHeightPx = with(density) { maxHeight.toPx() + statusBarHeightPx }

    // Sets the app bar's height offset limit to hide just the bottom title area and keep top title
    // visible when collapsed.
    SideEffect {
        if (scrollBehavior?.state?.heightOffsetLimit != pinnedHeightPx - maxHeightPx) {
            scrollBehavior?.state?.heightOffsetLimit = pinnedHeightPx - maxHeightPx
        }
    }

    val collapsedFraction by rememberUpdatedState(
        scrollBehavior?.state?.collapsedFraction ?: 0f
    )

    val height by rememberUpdatedState(
        with(density) {
            (maxHeightPx + (scrollBehavior?.state?.heightOffset ?: 0f)).toDp()
        }
    )

    // Set up support for resizing the top bar when vertically dragging the bar itself.
    val topBarDragModifier = if (scrollBehavior != null && !scrollBehavior.isPinned) {
        Modifier
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    scrollBehavior.state.heightOffset =
                        scrollBehavior.state.heightOffset + delta
                },
                onDragStopped = { velocity ->
                    settleAppBar(
                        scrollBehavior.state,
                        velocity,
                        scrollBehavior.flingAnimationSpec,
                        scrollBehavior.snapAnimationSpec
                    )
                }
            )
    } else Modifier

    val variableTextStyle by remember(collapsedFraction) {
        derivedStateOf {
            lerp(
                start = smallTitleTextStyle,
                stop = largeTitleTextStyle,
                fraction = 1f - collapsedFraction
            )
        }
    }

    val titleStartPadding by remember(collapsedFraction) {
        derivedStateOf {
            lerp(
                start = 24.dp,
                stop = if (navigationButton != null) {
                    ComponentsTokens.TopBar.iconButtonContainerSize + 16.dp
                } else 24.dp,
                fraction = collapsedFraction
            )
        }
    }

    val titleBottomPadding by remember(collapsedFraction) {
        derivedStateOf {
            lerp(
                start = 21.dp,
                stop = if (navigationButton != null) pinnedHeight else 21.dp,
                fraction = 1f - collapsedFraction
            )
        }
    }

    Column(
        modifier = Modifier
            .testTag("top_bar")
            .fillMaxWidth()
            .clipToBounds()
            .background(backgroundColor)
            .windowInsetsPadding(windowInsets.only(WindowInsetsSides.Horizontal))
            .then(topBarDragModifier)
            .then(modifier)
    ) {

        CompositionLocalProvider(
            LocalContentColor provides contentColor
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height),
                contentAlignment = Alignment.BottomStart
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(
                            start = titleStartPadding,
                            end = 48.dp,
                            bottom = titleBottomPadding
                        ),
                    contentAlignment = Alignment.BottomStart,
                    content = {

                        ProvideTextStyle(
                            value = variableTextStyle,
                            content = { Text(text = title) }
                        )

                    }
                )

                Row(
                    modifier = Modifier.height(pinnedHeight),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (navigationButton != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        navigationButton()
                        Spacer(modifier = Modifier.width(8.dp))
                    } else {
                        Spacer(modifier = Modifier.width(20.dp))
                    }

                }

            }

        }

        content?.let { it() }

        Divider(
            modifier = Modifier.alpha(collapsedFraction),
            color = ComponentsTokens.Separator.color,
            thickness = ComponentsTokens.Separator.thickness
        )

    }

}