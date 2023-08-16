package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

/** CompositionLocal key for passing [PaddingValues] down the composition. */
val LocalPaddingValues = compositionLocalOf { PaddingValues() }

/**
 * A composable function used for providing [PaddingValues] from a parent composable
 * e.g. from [Scaffold] to its children composables.
 * @param paddingValues the padding values for children composables to consume.
 * @param content the content of the parent composable (the children composables).
 **/
@Composable
fun ProvidePaddingValues(
    paddingValues: PaddingValues,
    content: @Composable () -> Unit
) = CompositionLocalProvider(
    LocalPaddingValues provides paddingValues,
    content = content
)