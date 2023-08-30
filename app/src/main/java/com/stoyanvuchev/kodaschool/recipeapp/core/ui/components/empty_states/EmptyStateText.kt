package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.empty_states

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme

@Composable
fun EmptyStateText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Theme.colors.outline,
    textStyle: TextStyle = Theme.typography.sectionTitle
) = Text(
    modifier = modifier,
    text = text,
    style = textStyle,
    color = color,
    textAlign = TextAlign.Center
)