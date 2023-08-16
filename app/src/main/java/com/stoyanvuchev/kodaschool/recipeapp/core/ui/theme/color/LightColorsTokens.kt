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

/** The default light color scheme tokens. */
@Immutable
object LightColorsTokens {
    val Primary: Color get() = ColorPaletteTokens.Primary40
    val OnPrimary: Color get() = ColorPaletteTokens.Primary100
    val PrimaryContainer: Color get() = ColorPaletteTokens.Primary90
    val OnPrimaryContainer: Color get() = ColorPaletteTokens.Primary10
    val Accent: Color get() = ColorPaletteTokens.Accent40
    val OnAccent: Color get() = ColorPaletteTokens.Accent100
    val AccentContainer: Color get() = ColorPaletteTokens.Accent95
    val OnAccentContainer: Color get() = ColorPaletteTokens.Accent10
    val Background: Color get() = ColorPaletteTokens.Background99
    val OnBackground: Color get() = ColorPaletteTokens.Background10
    val BackgroundContainer: Color get() = ColorPaletteTokens.Background95
    val OnBackgroundContainer: Color get() = ColorPaletteTokens.Background20
    val Error: Color get() = ColorPaletteTokens.Error40
    val OnError: Color get() = ColorPaletteTokens.Error100
    val ErrorContainer: Color get() = ColorPaletteTokens.Error80
    val OnErrorContainer: Color get() = ColorPaletteTokens.Error10
    val Outline: Color get() = ColorPaletteTokens.Background70
    val OutlineVariant: Color get() = ColorPaletteTokens.Background90
}