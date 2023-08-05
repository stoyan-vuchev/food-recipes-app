package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.typography

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.stoyanvuchev.kodaschool.recipeapp.R

/** Font families used by the design system. */
object FontFamilyTokens {

    /** Default font family. */
    val DMSans = FontFamily(
        Font(resId = R.font.dm_sans_regular, weight = FontWeight.Normal),
        Font(resId = R.font.dm_sans_medium, weight = FontWeight.Medium),
        Font(resId = R.font.dm_sans_bold, weight = FontWeight.Bold)
    )

}