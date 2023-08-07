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
import androidx.compose.ui.graphics.lerp

/** A collection of the default color palette tokens. */
@Immutable
object ColorPaletteTokens {

    val Primary0: Color get() = Color(0, 0, 0)
    val Primary10: Color get() = Color(0, 25, 71)
    val Primary20: Color get() = Color(0, 44, 113)
    val Primary25: Color get() = Color(10, 55, 131)
    val Primary30: Color get() = Color(29, 67, 143)
    val Primary35: Color get() = Color(44, 79, 156)
    val Primary40: Color get() = Color(57, 91, 169)
    val Primary50: Color get() = Color(84, 116, 196)
    val Primary60: Color get() = Color(110, 142, 223)
    val Primary70: Color get() = Color(137, 169, 252)
    val Primary80: Color get() = Color(177, 197, 255)
    val Primary90: Color get() = Color(218, 226, 255)
    val Primary95: Color get() = Color(238, 240, 255)
    val Primary98: Color get() = Color(250, 248, 255)
    val Primary99: Color get() = Color(254, 251, 255)
    val Primary100: Color get() = Color(255, 255, 255)

    val Accent0: Color get() = Color(0, 0, 0)
    val Accent10: Color get() = Color(44, 23, 0)
    val Accent20: Color get() = Color(73, 41, 0)
    val Accent25: Color get() = Color(88, 51, 0)
    val Accent30: Color get() = Color(104, 60, 0)
    val Accent35: Color get() = Color(120, 71, 0)
    val Accent40: Color get() = Color(137, 81, 0)
    val Accent50: Color get() = Color(172, 103, 0)
    val Accent60: Color get() = Color(207, 126, 6)
    val Accent70: Color get() = Color(239, 152, 42)
    val Accent80: Color get() = Color(255, 184, 108)
    val Accent90: Color get() = Color(255, 220, 188)
    val Accent95: Color get() = Color(255, 238, 224)
    val Accent98: Color get() = Color(255, 248, 244)
    val Accent99: Color get() = Color(255, 251, 255)
    val Accent100: Color get() = Color(255, 255, 255)

    val Error0: Color get() = Color(0, 0, 0)
    val Error10: Color get() = Color(65, 0, 2)
    val Error20: Color get() = Color(105, 0, 5)
    val Error25: Color get() = Color(126, 0, 7)
    val Error30: Color get() = Color(147, 0, 10)
    val Error35: Color get() = Color(168, 7, 16)
    val Error40: Color get() = Color(186, 26, 26)
    val Error50: Color get() = Color(222, 55, 48)
    val Error60: Color get() = Color(255, 84, 73)
    val Error70: Color get() = Color(255, 137, 125)
    val Error80: Color get() = Color(255, 180, 171)
    val Error90: Color get() = Color(255, 218, 214)
    val Error95: Color get() = Color(255, 237, 234)
    val Error98: Color get() = Color(255, 248, 247)
    val Error99: Color get() = Color(255, 251, 255)
    val Error100: Color get() = Color(255, 255, 255)

    val Background0: Color get() = Color(0, 0, 0)
    val Background10: Color get() = Color(25, 27, 35)
    val Background20: Color get() = Color(46, 48, 56)
    val Background15: Color get() = lerp(Background10, Background20, .5f)
    val Background25: Color get() = Color(57, 59, 67)
    val Background30: Color get() = Color(68, 70, 79)
    val Background35: Color get() = Color(80, 82, 90)
    val Background40: Color get() = Color(92, 94, 103)
    val Background50: Color get() = Color(117, 119, 128)
    val Background60: Color get() = Color(143, 144, 153)
    val Background70: Color get() = Color(170, 171, 180)
    val Background80: Color get() = Color(197, 198, 208)
    val Background90: Color get() = Color(225, 226, 236)
    val Background95: Color get() = Color(240, 240, 250)
    val Background98: Color get() = Color(250, 248, 255)
    val Background99: Color get() = Color(254, 251, 255)
    val Background100: Color get() = Color(255, 255, 255)

}