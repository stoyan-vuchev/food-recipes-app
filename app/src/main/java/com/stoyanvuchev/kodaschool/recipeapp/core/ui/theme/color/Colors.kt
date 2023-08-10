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

package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.color

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

/**
 * A custom color scheme based on the app design guidelines and the Material3 color scheme skeleton.
 * @param primary Use this color on commonly used and emphasised components.
 * @param onPrimary Use this color on top of the [primary] color.
 * @param primaryContainer Use this color on commonly used, but not emphasised components.
 * @param onPrimaryContainer Use this color on top of the [primaryContainer] color.
 * @param accent Use this color on the least used and emphasised components.
 * @param onAccent Use this color on top of the [accent] color.
 * @param accentContainer Use this color on the least used, but not emphasised components.
 * @param onAccentContainer Use this color on top of the [accentContainer] color.
 * @param background Use this color as the main background color across the whole app.
 * @param onBackground Use this color on top of the [background] color.
 * @param backgroundContainer Use this color on a slightly emphasised components on top of the [background] color.
 * @param onBackgroundContainer Use this color on top of the [backgroundContainer] color.
 * @param error Use this color to indicate errors on components on top of a [background] color.
 * @param onError Use this color on top of the [error] color.
 * @param errorContainer Use this color as a background on components with an error.
 * @param onErrorContainer Use this color on top of the [errorContainer] color.
 * @param outline Use this color as a border color or on non-emphasised components.
 * @param outlineVariant Use this color on separators.
 **/
@Stable
class Colors(
    primary: Color,
    onPrimary: Color,
    primaryContainer: Color,
    onPrimaryContainer: Color,
    accent: Color,
    onAccent: Color,
    accentContainer: Color,
    onAccentContainer: Color,
    background: Color,
    onBackground: Color,
    backgroundContainer: Color,
    onBackgroundContainer: Color,
    error: Color,
    onError: Color,
    errorContainer: Color,
    onErrorContainer: Color,
    outline: Color,
    outlineVariant: Color
) {

    /** Use this color on commonly used and emphasised components. */
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        internal set

    /** Use this color on top of the [primary] color. */
    var onPrimary by mutableStateOf(onPrimary, structuralEqualityPolicy())
        internal set

    /** Use this color on commonly used, but not emphasised components. */
    var primaryContainer by mutableStateOf(primaryContainer, structuralEqualityPolicy())
        internal set

    /** Use this color on top of the [primaryContainer] color. */
    var onPrimaryContainer by mutableStateOf(onPrimaryContainer, structuralEqualityPolicy())
        internal set

    /** Use this color on the least used and emphasised components. */
    var accent by mutableStateOf(accent, structuralEqualityPolicy())
        internal set

    /** Use this color on top of the [accent] color. */
    var onAccent by mutableStateOf(onAccent, structuralEqualityPolicy())
        internal set

    /** Use this color on the least used, but not emphasised components. */
    var accentContainer by mutableStateOf(accentContainer, structuralEqualityPolicy())
        internal set

    /** Use this color on top of the [accentContainer] color. */
    var onAccentContainer by mutableStateOf(onAccentContainer, structuralEqualityPolicy())
        internal set

    /** Use this color as the main background color across the whole app. */
    var background by mutableStateOf(background, structuralEqualityPolicy())
        internal set

    /** Use this color on top of the [background] color. */
    var onBackground by mutableStateOf(onBackground, structuralEqualityPolicy())
        internal set

    /** Use this color on slightly emphasised components on top of the [background] color. */
    var backgroundContainer by mutableStateOf(backgroundContainer, structuralEqualityPolicy())
        internal set

    /** Use this color on top of the [backgroundContainer] color. */
    var onBackgroundContainer by mutableStateOf(onBackgroundContainer, structuralEqualityPolicy())
        internal set

    /** Use this color to indicate errors on components on top of a [background] color. */
    var error by mutableStateOf(error, structuralEqualityPolicy())
        internal set

    /** Use this color on top of the [error] color. */
    var onError by mutableStateOf(onError, structuralEqualityPolicy())
        internal set

    /** Use this color as a background on components with an error. */
    var errorContainer by mutableStateOf(errorContainer, structuralEqualityPolicy())
        internal set

    /** Use this color on top of the [errorContainer] color. */
    var onErrorContainer by mutableStateOf(onErrorContainer, structuralEqualityPolicy())
        internal set

    /** Use this color as a border color or on non-emphasised components. */
    var outline by mutableStateOf(outline, structuralEqualityPolicy())
        internal set

    /** Use this color on separators. */
    var outlineVariant by mutableStateOf(outlineVariant, structuralEqualityPolicy())
        internal set

    /** Returns a copy of this [Colors] class, optionally overriding some of the values. */
    fun copy(
        primary: Color = this.primary,
        onPrimary: Color = this.onPrimary,
        primaryContainer: Color = this.primaryContainer,
        onPrimaryContainer: Color = this.onPrimaryContainer,
        accent: Color = this.accent,
        onAccent: Color = this.onAccent,
        accentContainer: Color = this.accentContainer,
        onAccentContainer: Color = this.onAccentContainer,
        background: Color = this.background,
        onBackground: Color = this.onBackground,
        backgroundContainer: Color = this.backgroundContainer,
        onBackgroundContainer: Color = this.onBackgroundContainer,
        error: Color = this.error,
        onError: Color = this.onError,
        errorContainer: Color = this.errorContainer,
        onErrorContainer: Color = this.onErrorContainer,
        outline: Color = this.outline,
        outlineVariant: Color = this.outlineVariant
    ): Colors = Colors(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        accent = accent,
        onAccent = onAccent,
        accentContainer = accentContainer,
        onAccentContainer = onAccentContainer,
        background = background,
        onBackground = onBackground,
        backgroundContainer = backgroundContainer,
        onBackgroundContainer = onBackgroundContainer,
        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        outline = outline,
        outlineVariant = outlineVariant
    )

    override fun toString(): String {
        return "Colors(" +
                "primary=$primary" +
                "onPrimary=$onPrimary" +
                "primaryContainer=$primaryContainer" +
                "onPrimaryContainer=$onPrimaryContainer" +
                "accent=$accent" +
                "onAccent=$onAccent" +
                "accentContainer=$accentContainer" +
                "onAccentContainer=$onAccentContainer" +
                "error=$error" +
                "onError=$onError" +
                "errorContainer=$errorContainer" +
                "onErrorContainer=$onErrorContainer" +
                "background=$background" +
                "onBackground=$onBackground" +
                "backgroundContainer=$backgroundContainer" +
                "onBackgroundContainer=$onBackgroundContainer" +
                "outline=$outline" +
                "outlineVariant=$outlineVariant" +
                ")"
    }

}

/** Returns a light color scheme with the specified colors. */
fun lightColors(
    primary: Color = LightColorsTokens.Primary,
    onPrimary: Color = LightColorsTokens.OnPrimary,
    primaryContainer: Color = LightColorsTokens.PrimaryContainer,
    onPrimaryContainer: Color = LightColorsTokens.OnPrimaryContainer,
    accent: Color = LightColorsTokens.Accent,
    onAccent: Color = LightColorsTokens.OnAccent,
    accentContainer: Color = LightColorsTokens.AccentContainer,
    onAccentContainer: Color = LightColorsTokens.OnAccentContainer,
    background: Color = LightColorsTokens.Background,
    onBackground: Color = LightColorsTokens.OnBackground,
    backgroundContainer: Color = LightColorsTokens.BackgroundContainer,
    onBackgroundContainer: Color = LightColorsTokens.OnBackgroundContainer,
    error: Color = LightColorsTokens.Error,
    onError: Color = LightColorsTokens.OnError,
    errorContainer: Color = LightColorsTokens.ErrorContainer,
    onErrorContainer: Color = LightColorsTokens.OnErrorContainer,
    outline: Color = LightColorsTokens.Outline,
    outlineVariant: Color = LightColorsTokens.OutlineVariant
): Colors = Colors(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    accent = accent,
    onAccent = onAccent,
    accentContainer = accentContainer,
    onAccentContainer = onAccentContainer,
    background = background,
    onBackground = onBackground,
    backgroundContainer = backgroundContainer,
    onBackgroundContainer = onBackgroundContainer,
    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = onErrorContainer,
    outline = outline,
    outlineVariant = outlineVariant
)

/** Returns a dark color scheme with the specified colors. */
fun darkColors(
    primary: Color = DarkColorsTokens.Primary,
    onPrimary: Color = DarkColorsTokens.OnPrimary,
    primaryContainer: Color = DarkColorsTokens.PrimaryContainer,
    onPrimaryContainer: Color = DarkColorsTokens.OnPrimaryContainer,
    accent: Color = DarkColorsTokens.Accent,
    onAccent: Color = DarkColorsTokens.OnAccent,
    accentContainer: Color = DarkColorsTokens.AccentContainer,
    onAccentContainer: Color = DarkColorsTokens.OnAccentContainer,
    background: Color = DarkColorsTokens.Background,
    onBackground: Color = DarkColorsTokens.OnBackground,
    backgroundContainer: Color = DarkColorsTokens.BackgroundContainer,
    onBackgroundContainer: Color = DarkColorsTokens.OnBackgroundContainer,
    error: Color = DarkColorsTokens.Error,
    onError: Color = DarkColorsTokens.OnError,
    errorContainer: Color = DarkColorsTokens.ErrorContainer,
    onErrorContainer: Color = DarkColorsTokens.OnErrorContainer,
    outline: Color = DarkColorsTokens.Outline,
    outlineVariant: Color = DarkColorsTokens.OutlineVariant
): Colors = Colors(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    accent = accent,
    onAccent = onAccent,
    accentContainer = accentContainer,
    onAccentContainer = onAccentContainer,
    background = background,
    onBackground = onBackground,
    backgroundContainer = backgroundContainer,
    onBackgroundContainer = onBackgroundContainer,
    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = onErrorContainer,
    outline = outline,
    outlineVariant = outlineVariant
)

/** CompositionLocal key used for passing [Colors] down the composition for consumption. */
val LocalColors = staticCompositionLocalOf { lightColors() }

/**
 * Updates the internal values of a given [Colors] with values from the [other] [Colors].
 * This allows efficiently updating a subset of [Colors], without recomposing every
 * composable that consumes values from [LocalColors].
 *
 * Because [Colors] is very wide-reaching, and used by many expensive composables in the
 * hierarchy, providing a new value to [LocalColors] causes every composable consuming
 * [LocalColors] to recompose, which is prohibitively expensive in cases such as animating one
 * color in the theme. Instead, [Colors] is internally backed by [mutableStateOf], and this
 * function mutates the internal state of [this] to match values in [other]. This means that any
 * changes will mutate the internal state of [this], and only cause composables that are reading the
 * specific changed value to recompose.
 */
internal fun Colors.updateColorsFrom(other: Colors) {
    primary = other.primary
    onPrimary = other.onPrimary
    primaryContainer = other.primaryContainer
    onPrimaryContainer = other.onPrimaryContainer
    accent = other.accent
    onAccent = other.onAccent
    accentContainer = other.accentContainer
    onAccentContainer = other.onAccentContainer
    background = other.background
    onBackground = other.onBackground
    backgroundContainer = other.backgroundContainer
    onBackgroundContainer = other.onBackgroundContainer
    error = other.error
    onError = other.onError
    errorContainer = other.errorContainer
    onErrorContainer = other.onErrorContainer
    outline = other.outline
    outlineVariant = other.outlineVariant
}