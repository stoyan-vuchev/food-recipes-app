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
 * @param outline Use this color as a border color.
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

    /** Use this color as a border color. */
    var outline by mutableStateOf(outline, structuralEqualityPolicy())
        internal set

    /** Use this color on separators. */
    var outlineVariant by mutableStateOf(outlineVariant, structuralEqualityPolicy())
        internal set

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

/** CompositionLocal used to pass [Colors] down the composition for consumption. */
val LocalColors = staticCompositionLocalOf { lightColors() }