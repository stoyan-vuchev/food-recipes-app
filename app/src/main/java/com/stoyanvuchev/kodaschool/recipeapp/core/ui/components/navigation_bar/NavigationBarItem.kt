package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.navigation_bar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow

/**
 * An item for a [NavigationBar].
 * @param selected Whether the item is selected or not.
 * @param onSelected Callback invoked when the item is being selected.
 * @param icon The item icon painter.
 * @param label The item label text.
 **/
@Composable
fun NavigationBarScope.NavigationBarItem(
    selected: Boolean,
    onSelected: () -> Unit,
    icon: Painter,
    label: String
) {

    val color by animateColorAsState(
        targetValue = LocalNavigationBarColors.current.itemColor(selected).value,
        label = ""
    )

    CompositionLocalProvider(
        LocalContentColor provides color,
        content = {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(
                        enabled = !selected,
                        onClick = onSelected
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {

                    Icon(
                        modifier = Modifier.size(NavigationBarDefaults.itemIconSize),
                        painter = icon,
                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier.height(
                            NavigationBarDefaults.itemContainerVerticalSpacing
                        )
                    )

                    Text(
                        modifier = Modifier.padding(
                            horizontal = NavigationBarDefaults.containerHorizontalSpacing
                        ),
                        text = label,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            )

        }
    )

}