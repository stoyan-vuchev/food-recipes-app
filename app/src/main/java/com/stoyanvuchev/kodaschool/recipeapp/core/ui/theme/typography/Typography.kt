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