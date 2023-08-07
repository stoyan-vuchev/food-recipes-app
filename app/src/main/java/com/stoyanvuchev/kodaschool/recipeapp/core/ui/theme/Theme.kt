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

package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.color.Colors
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.color.LocalColors
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.color.darkColors
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.color.lightColors
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.color.updateColorsFrom
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.shape.LocalShapes
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.shape.Shapes
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.typography.LocalTypography
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.typography.Typography
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.typography.TypographyTokens

@Composable
fun FoodRecipesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) = Theme(
    darkTheme = darkTheme,
    content = content
)


@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: Colors = if (darkTheme) darkColors() else lightColors(),
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Unspecified,
            darkIcons = !darkTheme
        )
        systemUiController.setNavigationBarColor(
            color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                Color.Unspecified
            } else Color.Black.copy(alpha = .25f),
            darkIcons = !darkTheme,
            navigationBarContrastEnforced = false
        )
    }

    // Explicitly creating a new object so we don't mutate
    // the initially provided colors, and overwrite the values.
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides Typography(),
        LocalShapes provides Shapes(),
        LocalTextStyle provides TypographyTokens.RegularText,
        content = { MaterialTheme(content = content) }
    )

}

/** A custom theme based on the design system and Material3 skeleton. */
object Theme {

    /** Retrieves the current [Colors] CompositionLocal key value. */
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    /** Retrieves the current [Typography] CompositionLocal key value. */
    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    /** Retrieves the current [Shapes] CompositionLocal key value. */
    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

}