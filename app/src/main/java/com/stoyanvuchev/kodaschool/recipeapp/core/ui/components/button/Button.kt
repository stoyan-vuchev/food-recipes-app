package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle

/**
 * A custom Button component based on the design system and the Material3 skeleton.
 * @param modifier Used for further customization.
 * @param onClick A callback invoked whenever the Button is clicked.
 * @param enabled Indicates whether the Button is enabled or not.
 * @param colors The Button colors.
 * @param shape The Button shape.
 * @param textStyle Applies a [TextStyle] to the Button text.
 * @param content The content of the Button.
 **/
@Composable
fun Button(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.colors(),
    shape: Shape = ButtonDefaults.shape,
    textStyle: TextStyle = ButtonDefaults.textStyle,
    content: @Composable RowScope.() -> Unit
) {

    val stateTransition = updateTransition(
        targetState = enabled,
        label = null
    )

    val backgroundColor by stateTransition.animateColor(
        targetValueByState = { colors.background(enabled = it).value },
        label = ""
    )

    val contentColor by stateTransition.animateColor(
        targetValueByState = { colors.content(enabled = it).value },
        label = ""
    )

    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalTextStyle provides textStyle,
        content = {

            Row(
                modifier = Modifier
                    .defaultMinSize(
                        minWidth = ButtonDefaults.defaultMinSize.width,
                        minHeight = ButtonDefaults.defaultMinSize.height
                    )
                    .clip(shape = shape)
                    .background(color = backgroundColor)
                    .clickable(
                        enabled = true,
                        role = Role.Button,
                        onClick = onClick
                    )
                    .padding(
                        horizontal = ButtonDefaults.horizontalPadding,
                        vertical = ButtonDefaults.verticalPadding
                    )
                    .then(modifier),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                content = content
            )

        }
    )

}