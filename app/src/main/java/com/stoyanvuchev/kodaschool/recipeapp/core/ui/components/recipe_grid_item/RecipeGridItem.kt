package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.recipe_grid_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel

@Composable
fun RecipeGridItem(
    modifier: Modifier = Modifier,
    recipe: RecipeModel,
    enabled: Boolean,
    isSaveButtonVisible: Boolean = true,
    onSave: () -> Unit,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier
            .defaultMinSize(minWidth = 128.dp)
            .clip(Theme.shapes.medium)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                enabled = enabled,
                onClick = onClick
            )
    ) {

        recipe.thumbnail?.let {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(Theme.shapes.medium)
                    .background(color = Theme.colors.backgroundContainer),
                bitmap = it.asImageBitmap(),
                contentDescription = null
            )

        } ?: Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(Theme.shapes.medium)
                .background(color = Theme.colors.backgroundContainer)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            Text(
                text = recipe.label,
                style = Theme.typography.sectionTitle,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Theme.colors.onBackground
            )

        }

        if (isSaveButtonVisible) {

            RecipeGridItemSaveButton(
                saved = recipe.isBookmarked,
                enabled = enabled,
                onClick = onSave
            )

        }

    }

}

@Preview(showBackground = true)
@Composable
fun RecipeGridItemPreview() {
    FoodRecipesTheme {
        var isBookmarked by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .width(239.dp)
                .padding(32.dp)
        ) {
            RecipeGridItem(
                recipe = RecipeModel(
                    label = "UpsideDown Cake",
                    isBookmarked = isBookmarked
                ),
                enabled = true,
                onSave = { isBookmarked = !isBookmarked },
                onClick = {}
            )
        }
    }
}