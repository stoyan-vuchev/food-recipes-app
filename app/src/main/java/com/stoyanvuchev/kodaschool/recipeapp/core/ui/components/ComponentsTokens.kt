package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme

/** Contains all components tokens (for dimensions, colors, etc.) based on the design system and the Material3 skeleton. */
object ComponentsTokens {

    // Navigation bar

    val NavigationBarContainerHeight: Dp get() = 67.0.dp
    val NavigationBarBackgroundColor: Color @Composable get() = Theme.colors.background
    val NavigationBarSeparatorColor: Color @Composable get() = Theme.colors.outlineVariant
    val NavigationBarSelectedItemColor: Color @Composable get() = Theme.colors.primary
    val NavigationBarUnselectedItemColor: Color @Composable get() = Theme.colors.outline
    val NavigationBarItemLabelTextStyle: TextStyle @Composable get() = Theme.typography.regularText
    val NavigationBarItemIconSize: Dp get() = 24.0.dp
    val NavigationBarContainerHorizontalSpacing: Dp get() = 8.0.dp
    val NavigationBarItemContainerVerticalSpacing: Dp get() = 8.0.dp
    val NavigationBarWindowInsets: WindowInsets
        @Composable get() = WindowInsets.systemBarsForUiComponents
            .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)

}