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

package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme

/** Contains all components tokens (for dimensions, colors, etc.) based on the design system and the Material3 skeleton. */
object ComponentsTokens {

    // Separator

    val SeparatorColor: Color @Composable get() = Theme.colors.outlineVariant
    val SeparatorThickness: Dp get() = 1.0.dp

    // Navigation bar

    val NavigationBarContainerHeight: Dp get() = 67.0.dp
    val NavigationBarBackgroundColor: Color @Composable get() = Theme.colors.background
    val NavigationBarSelectedItemColor: Color @Composable get() = Theme.colors.primary
    val NavigationBarUnselectedItemColor: Color @Composable get() = Theme.colors.outline

    val NavigationBarItemLabelTextStyle: TextStyle
        @Composable get() = Theme.typography.regularText.copy(fontWeight = FontWeight.Medium)

    val NavigationBarItemIconSize: Dp get() = 20.0.dp
    val NavigationBarContainerHorizontalSpacing: Dp get() = 8.0.dp
    val NavigationBarItemContainerVerticalSpacing: Dp get() = 8.0.dp
    val NavigationBarWindowInsets: WindowInsets
        @Composable get() = WindowInsets.systemBarsForUiComponents
            .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)

    // Category bar

    val CategoryBarContainerHeight: Dp get() = 56.0.dp
    val CategoryBarItemContainerHeight: Dp get() = 30.0.dp
    val CategoryBarSelectedItemBackgroundColor: Color @Composable get() = Theme.colors.primary
    val CategoryBarSelectedItemContentColor: Color @Composable get() = Theme.colors.onPrimary
    val CategoryBarUnselectedItemBackgroundColor: Color @Composable get() = Theme.colors.backgroundContainer
    val CategoryBarUnselectedItemContentColor: Color @Composable get() = Theme.colors.onBackgroundContainer
    val CategoryBarContainerHorizontalSpacing: Dp get() = 10.0.dp
    val CategoryBarContainerHorizontalPadding: Dp get() = 32.0.dp
    val CategoryBarItemContainerHorizontalPadding: Dp get() = 12.0.dp
    val CategoryBarItemShape: Shape @Composable get() = Theme.shapes.extraLarge
    val CategoryBarItemTextStyle: TextStyle
        @Composable get() = Theme.typography.regularText.copy(fontWeight = FontWeight.SemiBold)

    // Button

    val ButtonBackgroundColor: Color @Composable get() = Theme.colors.primary
    val ButtonContentColor: Color @Composable get() = Theme.colors.onPrimary
    val ButtonDisabledBackgroundColor: Color @Composable get() = Theme.colors.background.copy(.16f)
    val ButtonDisabledContentColor: Color @Composable get() = Theme.colors.onBackground.copy(.38f)
    val ButtonShape: Shape @Composable get() = Theme.shapes.extraLarge
    val ButtonHorizontalPadding: Dp get() = 10.0.dp
    val ButtonVerticalPadding: Dp get() = 6.0.dp
    val ButtonDefaultMinSize: DpSize get() = DpSize(width = 32.dp, height = 24.dp)
    val ButtonTextStyle: TextStyle
        @Composable get() = Theme.typography.regularText.copy(fontWeight = FontWeight.Medium)

}