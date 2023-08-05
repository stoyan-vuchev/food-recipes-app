package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.typography

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/** Typography tokens used by the design system. */
@Immutable
object TypographyTokens {

    val RegularText: TextStyle
        get() = DefaultTextStyle.copy(
            fontFamily = FontFamilyTokens.DMSans,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            letterSpacing = 0.sp
        )

    val SectionTitle: TextStyle
        get() = DefaultTextStyle.copy(
            fontFamily = FontFamilyTokens.DMSans,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            letterSpacing = 0.sp
        )

    val LargeTitle: TextStyle
        get() = DefaultTextStyle.copy(
            fontFamily = FontFamilyTokens.DMSans,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            letterSpacing = 0.sp
        )

}

@Suppress("DEPRECATION")
@Stable
val DefaultTextStyle = TextStyle.Default.copy(
    platformStyle = PlatformTextStyle(includeFontPadding = true)
)