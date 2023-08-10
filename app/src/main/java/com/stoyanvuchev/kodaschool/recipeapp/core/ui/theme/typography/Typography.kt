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

package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.typography

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

/**
 * A custom typography styles based on the design system and the Material3 skeleton.
 * @param regularText Use this style on a long form text or as a label in components.
 * @param sectionTitle Use this style on a section titles.
 * @param largeTitle Use this style on a large titles.
 **/
@Immutable
class Typography(
    val regularText: TextStyle = TypographyTokens.RegularText,
    val sectionTitle: TextStyle = TypographyTokens.SectionTitle,
    val largeTitle: TextStyle = TypographyTokens.LargeTitle,
) {

    /** Returns a copy of this Typography, optionally overriding some of the values. */
    fun copy(
        regularText: TextStyle = this.regularText,
        sectionTitle: TextStyle = this.sectionTitle,
        largeTitle: TextStyle = this.largeTitle
    ): Typography = Typography(
        regularText = regularText,
        sectionTitle = sectionTitle,
        largeTitle = largeTitle
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Typography) return false
        if (regularText != other.regularText) return false
        if (sectionTitle != other.sectionTitle) return false
        if (largeTitle != other.largeTitle) return false
        return true
    }

    override fun hashCode(): Int {
        var result = regularText.hashCode()
        result = 31 * result + sectionTitle.hashCode()
        result = 31 * result + largeTitle.hashCode()
        return result
    }

    override fun toString(): String {
        return "Typography(" +
                "regularText=$regularText, " +
                "sectionTitle=$sectionTitle, " +
                "largeTitle=$largeTitle" +
                ")"
    }

}

/** CompositionLocal key used to pass [Typography] down the composition for consumption. */
val LocalTypography = staticCompositionLocalOf { Typography() }