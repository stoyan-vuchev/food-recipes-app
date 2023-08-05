package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.recipe_grid_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel

@Composable
fun RecipeGridItem(
    modifier: Modifier = Modifier,
    recipe: RecipeModel,
    enabled: Boolean,
    onSave: () -> Unit,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .clip(Theme.shapes.medium)
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .then(modifier)
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(Theme.shapes.medium)
                .background(color = Theme.colors.backgroundContainer),
            model = recipe.imageSmall,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = recipe.label,
            style = Theme.typography.sectionTitle.copy(
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Theme.colors.onBackground
        )

        Spacer(modifier = Modifier.height(12.dp))

        RecipeGridItemSaveButton(
            saved = recipe.isBookmarked,
            enabled = enabled,
            onClick = onSave
        )

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