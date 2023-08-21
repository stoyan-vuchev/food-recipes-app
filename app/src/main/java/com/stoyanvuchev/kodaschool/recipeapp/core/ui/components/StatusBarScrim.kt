package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme

@Composable
fun StatusBarScrim(
    modifier: Modifier = Modifier
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .background(color = Theme.colors.background)
        .then(modifier),
    content = {

        Spacer(modifier = Modifier.statusBarsPadding())

        Divider(
            color = ComponentsTokens.Separator.color,
            thickness = ComponentsTokens.Separator.thickness
        )

    }
)