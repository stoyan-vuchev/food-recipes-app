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

    object Separator {

        val color: Color @Composable get() = Theme.colors.outlineVariant
        val thickness: Dp get() = 1.0.dp

    }

    // Navigation bar

    object NavigationBar {

        val containerHeight: Dp get() = 67.0.dp
        val backgroundColor: Color @Composable get() = Theme.colors.background
        val selectedItemColor: Color @Composable get() = Theme.colors.primary
        val unselectedItemColor: Color @Composable get() = Theme.colors.outline

        val itemIconSize: Dp get() = 20.0.dp
        val containerHorizontalSpacing: Dp get() = 8.0.dp
        val itemContainerVerticalSpacing: Dp get() = 8.0.dp

        val itemLabelTextStyle: TextStyle
            @Composable get() = Theme.typography.regularText.copy(
                fontWeight = FontWeight.Medium
            )

        val windowInsets: WindowInsets
            @Composable get() = WindowInsets.systemBarsForUiComponents
                .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)

    }

    // Category bar

    object CategoryBar {

        val containerHeight: Dp get() = 56.0.dp
        val itemContainerHeight: Dp get() = 30.0.dp

        val selectedItemBackgroundColor: Color @Composable get() = Theme.colors.primary
        val selectedItemContentColor: Color @Composable get() = Theme.colors.onPrimary
        val unselectedItemBackgroundColor: Color @Composable get() = Theme.colors.backgroundContainer
        val unselectedItemContentColor: Color @Composable get() = Theme.colors.onBackgroundContainer

        val containerHorizontalSpacing: Dp get() = 10.0.dp
        val containerHorizontalPadding: Dp get() = 24.0.dp
        val itemContainerHorizontalPadding: Dp get() = 12.0.dp

        val itemShape: Shape @Composable get() = Theme.shapes.extraLarge

        val itemTextStyle: TextStyle
            @Composable get() = Theme.typography.regularText.copy(
                fontWeight = FontWeight.SemiBold
            )

    }

    // Button

    object Button {

        val backgroundColor: Color @Composable get() = Theme.colors.primary
        val contentColor: Color @Composable get() = Theme.colors.onPrimary
        val disabledBackgroundColor: Color @Composable get() = Theme.colors.background.copy(.16f)
        val disabledContentColor: Color @Composable get() = Theme.colors.onBackground.copy(.38f)

        val shape: Shape @Composable get() = Theme.shapes.extraLarge

        val horizontalPadding: Dp get() = 10.0.dp
        val verticalPadding: Dp get() = 6.0.dp
        val defaultMinSize: DpSize get() = DpSize(width = 32.dp, height = 24.dp)

        val textStyle: TextStyle
            @Composable get() = Theme.typography.regularText.copy(fontWeight = FontWeight.Medium)

    }

    // TopBar

    object TopBar {

        val smallContainerHeight: Dp = 64.0.dp
        val largeContainerHeight: Dp = 128.0.dp

        val backgroundColor: Color @Composable get() = Theme.colors.background
        val contentColor: Color @Composable get() = Theme.colors.onBackground

        val iconButtonIconSize: Dp = 24.0.dp
        val iconButtonContainerSize: Dp = 48.0.dp

        val smallTitleTextStyle: TextStyle @Composable get() = Theme.typography.sectionTitle.copy(
            fontWeight = FontWeight.Bold
        )

        val largeTitleTextStyle: TextStyle @Composable get() = Theme.typography.largeTitle

        val windowInsets: WindowInsets
            @Composable get() = WindowInsets.systemBarsForUiComponents
                .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)

    }

}