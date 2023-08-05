package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable

/** Consumes the system bars [WindowInsets] for UI components. */
internal val WindowInsets.Companion.systemBarsForUiComponents: WindowInsets
    @Composable get() = systemBars