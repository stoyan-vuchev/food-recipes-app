package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

/**
 * An item for a [CategoryBar].
 * @param selected Whether the item is selected or not.
 * @param onSelected Callback invoked when the item is being selected.
 * @param label The item label text.
 * @param contentType a factory of the content types for the item. The item compositions of
 * the same type could be reused more efficiently. Note that null is a valid type and items of such
 * type will be considered compatible.
 * @param key A stable and unique key representing the item. Using the same key
 *      * for multiple items in the list is not allowed. Type of the key should be saveable
 *      * via Bundle on Android. If null is passed the position in the list will represent the key.
 *      * When you specify the key the scroll position will be maintained based on the key, which
 *      * means if you add/remove items before the current visible item the item with the given key
 *      * will be kept as the first visible one.
 **/
fun CategoryBarScope.categoryBarItem(
    selected: Boolean,
    onSelected: () -> Unit,
    label: String,
    contentType: Any? = null,
    key: Any? = null
) = item(
    key = key,
    contentType = contentType,
    content = {

        CategoryBarItemContent(
            selected = selected,
            onSelected = onSelected,
            label = label
        )

    }
)

/**
 * The content of an item for a [CategoryBar].
 * @param selected Whether the item is selected or not.
 * @param onSelected Callback invoked when the item is being selected.
 * @param label The item label text.
 **/
@Composable
fun CategoryBarItemContent(
    selected: Boolean,
    onSelected: () -> Unit,
    label: String
) {

    val colors = LocalCategoryBarColors.current
    val stateTransition = updateTransition(
        targetState = selected,
        label = null
    )

    val backgroundColor by stateTransition.animateColor(
        targetValueByState = { colors.backgroundColor(selected = it).value },
        label = ""
    )

    val contentColor by stateTransition.animateColor(
        targetValueByState = { colors.contentColor(selected = it).value },
        label = ""
    )

    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        content = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(CategoryBarDefaults.itemContainerHeight)
                    .clip(CategoryBarDefaults.LocalCategoryItemShape.current)
                    .background(color = backgroundColor)
                    .clickable(
                        enabled = !selected,
                        onClick = onSelected
                    )
                    .padding(
                        horizontal = CategoryBarDefaults.itemContainerHorizontalPadding
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                content = { Text(text = label) }
            )

        }
    )

}