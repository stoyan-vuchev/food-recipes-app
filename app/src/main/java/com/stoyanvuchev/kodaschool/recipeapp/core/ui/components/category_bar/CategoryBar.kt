package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens

/**
 * A category bar component displaying [categoryBarItem]s.
 * @param modifier Used for further customization.
 * @param state The state of the Category bar.
 * @param colors Used for styling the Category bar colors.
 * @param horizontalContentPadding Used for additional horizontal padding before and after the [items].
 * @param itemTextStyle Used for styling the [categoryBarItem] label [TextStyle].
 * @param itemShape Used for styling the [categoryBarItem] with a shape.
 * @param items Displaying [categoryBarItem]s.
 **/
@Composable
fun CategoryBar(
    modifier: Modifier = Modifier,
    state: CategoryBarState = rememberCategoryBarState(),
    colors: CategoryBarColors = CategoryBarDefaults.colors(),
    horizontalContentPadding: Dp = ComponentsTokens.CategoryBarContainerHorizontalPadding,
    itemTextStyle: TextStyle = CategoryBarDefaults.itemTextStyle,
    itemShape: Shape = CategoryBarDefaults.itemShape,
    items: CategoryBarScope.() -> Unit
) {

    CompositionLocalProvider(
        LocalCategoryBarColors provides colors,
        LocalTextStyle provides itemTextStyle,
        CategoryBarDefaults.LocalCategoryItemShape provides itemShape,
        content = {

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(CategoryBarDefaults.containerHeight)
                    .testTag("category_bar")
                    .then(modifier),
                state = state,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    CategoryBarDefaults.containerHorizontalSpacing
                ),
                contentPadding = PaddingValues(horizontal = horizontalContentPadding),
                content = items
            )

        }
    )

}

/** Using [CategoryBarScope] as a typealias of [LazyListScope] to avoid confusion. */
typealias CategoryBarScope = LazyListScope