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

package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/** The default dark color scheme tokens. */
@Immutable
object DarkColorsTokens {
    val Primary: Color get() = ColorPaletteTokens.Primary80
    val OnPrimary: Color get() = ColorPaletteTokens.Primary10
    val PrimaryContainer: Color get() = ColorPaletteTokens.Primary25
    val OnPrimaryContainer: Color get() = ColorPaletteTokens.Primary90
    val Accent: Color get() = ColorPaletteTokens.Accent80
    val OnAccent: Color get() = ColorPaletteTokens.Accent20
    val AccentContainer: Color get() = ColorPaletteTokens.Accent25
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
    val OutlineVariant: Color get() = ColorPaletteTokens.Background15
}