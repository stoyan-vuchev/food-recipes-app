package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.recipe_grid_item

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button.Button
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button.ButtonDefaults
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme

/**
 * A [Button] to add or remove a recipe form saved.
 * @param modifier Further customization.
 * @param saved Indicates whether the recipe is saved or not.
 * @param enabled Indicates whether the [Button] is enabled or not.
 * @param onClick A callback invoked whenever the [Button] is clicked.
 **/
@Composable
fun RecipeGridItemSaveButton(
    modifier: Modifier = Modifier,
    saved: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {

    val stateTransition = updateTransition(
        targetState = saved,
        label = null
    )

    val backgroundColor by stateTransition.animateColor(
        targetValueByState = { if (it) Theme.colors.accent else Theme.colors.backgroundContainer },
        label = ""
    )

    val contentColor by stateTransition.animateColor(
        targetValueByState = { if (it) Theme.colors.onAccent else Theme.colors.onBackgroundContainer },
        label = ""
    )

    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.colors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        content = {

            Icon(
                modifier = Modifier.size(14.dp),
                painter = painterResource(id = R.drawable.saved_icon),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(ButtonDefaults.horizontalPadding * 0.75f))

            Text(
                text = stringResource(
                    id = if (saved) R.string.saved_recipe_button_label
                    else R.string.save_recipe_button_label
                )
            )

            Spacer(modifier = Modifier.width(ButtonDefaults.horizontalPadding * 0.25f))

        }
    )

}

@Preview(showBackground = true)
@Composable
private fun RecipeGridItemSaveButtonNotSavedPreview() {
    FoodRecipesTheme {

        Box(
            modifier = Modifier.size(175.dp),
            contentAlignment = Alignment.Center,
            content = {

                RecipeGridItemSaveButton(
                    saved = false,
                    enabled = true,
                    onClick = {}
                )

            }
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeGridItemSaveButtonSavedPreview() {
    FoodRecipesTheme {

        Box(
            modifier = Modifier.size(175.dp),
            contentAlignment = Alignment.Center,
            content = {

                RecipeGridItemSaveButton(
                    saved = true,
                    enabled = true,
                    onClick = {}
                )

            }
        )

    }
}