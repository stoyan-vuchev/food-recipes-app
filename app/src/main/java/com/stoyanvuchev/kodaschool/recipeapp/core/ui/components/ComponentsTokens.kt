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
    val CategoryBarItemContainerHeight: Dp get() = 32.0.dp
    val CategoryBarSelectedItemBackgroundColor: Color @Composable get() = Theme.colors.primary
    val CategoryBarSelectedItemContentColor: Color @Composable get() = Theme.colors.onPrimary
    val CategoryBarUnselectedItemBackgroundColor: Color @Composable get() = Theme.colors.primaryContainer
    val CategoryBarUnselectedItemContentColor: Color @Composable get() = Theme.colors.onPrimaryContainer
    val CategoryBarContainerHorizontalSpacing: Dp get() = 10.0.dp
    val CategoryBarContainerHorizontalPadding: Dp get() = 32.0.dp
    val CategoryBarItemContainerHorizontalPadding: Dp get() = 16.0.dp
    val CategoryBarItemShape: Shape @Composable get() = Theme.shapes.extraLarge
    val CategoryBarItemTextStyle: TextStyle
        @Composable get() = Theme.typography.regularText.copy(fontWeight = FontWeight.SemiBold)

    // Button

    val ButtonBackgroundColor: Color @Composable get() = Theme.colors.primary
    val ButtonContentColor: Color @Composable get() = Theme.colors.onPrimary
    val ButtonDisabledBackgroundColor: Color @Composable get() = Theme.colors.background.copy(.16f)
    val ButtonDisabledContentColor: Color @Composable get() = Theme.colors.onBackground.copy(.38f)
    val ButtonShape: Shape @Composable get() = Theme.shapes.extraLarge
    val ButtonHorizontalPadding: Dp get() = 16.0.dp
    val ButtonVerticalPadding: Dp get() = 8.0.dp
    val ButtonDefaultMinSize: DpSize get() = DpSize(width = 32.dp, height = 24.dp)
    val ButtonTextStyle: TextStyle
        @Composable get() = Theme.typography.regularText.copy(fontWeight = FontWeight.Medium)

}