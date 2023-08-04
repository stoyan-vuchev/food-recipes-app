package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/** The default dark color scheme tokens. */
@Immutable
object DarkColorsTokens {
    val Primary: Color get() = ColorPaletteTokens.Primary80
    val OnPrimary: Color get() = ColorPaletteTokens.Primary20
    val PrimaryContainer: Color get() = ColorPaletteTokens.Primary30
    val OnPrimaryContainer: Color get() = ColorPaletteTokens.Primary90
    val Accent: Color get() = ColorPaletteTokens.Accent80
    val OnAccent: Color get() = ColorPaletteTokens.Accent20
    val AccentContainer: Color get() = ColorPaletteTokens.Accent30
    val OnAccentContainer: Color get() = ColorPaletteTokens.Accent90
    val Background: Color get() = ColorPaletteTokens.Background10
    val OnBackground: Color get() = ColorPaletteTokens.Background90
    val BackgroundContainer: Color get() = ColorPaletteTokens.Background20
    val OnBackgroundContainer: Color get() = ColorPaletteTokens.Background80
    val Error: Color get() = ColorPaletteTokens.Error80
    val OnError: Color get() = ColorPaletteTokens.Error20
    val ErrorContainer: Color get() = ColorPaletteTokens.Error30
    val OnErrorContainer: Color get() = ColorPaletteTokens.Error90
    val Outline: Color get() = ColorPaletteTokens.Background60
    val OutlineVariant: Color get() = ColorPaletteTokens.Background30
}