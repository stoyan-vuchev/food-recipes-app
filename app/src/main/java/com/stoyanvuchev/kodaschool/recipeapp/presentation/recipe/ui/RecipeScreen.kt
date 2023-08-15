package com.stoyanvuchev.kodaschool.recipeapp.presentation.recipe.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toDrawable
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.NavigationBarScrim
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button.Button
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button.ButtonDefaults
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.topbar.TopBar
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.topbar.TopBarDefaults
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recipe.RecipeScreenState
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recipe.RecipeScreenUiAction
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffoldUtils

@Composable
fun RecipeScreen(
    screenState: RecipeScreenState,
    onUiAction: (RecipeScreenUiAction) -> Unit
) {

    val lazyListState = rememberLazyListState()
    val scrollBehavior = TopBarDefaults.exitUntilCollapsedScrollBehavior()
    val context = LocalContext.current

    val imageModel by rememberUpdatedState(
        ImageRequest.Builder(LocalContext.current)
            .data(screenState.recipe.imageRegular)
            .fallback(screenState.recipe.thumbnail?.toDrawable(context.resources))
            .allowConversionToBitmap(true)
            .crossfade(true)
            .build()
    )

    ResponsiveScaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Theme.colors.background,
        contentColor = Theme.colors.onBackground,
        topBar = {

            TopBar(
                title = stringResource(id = R.string.recipe),
                scrollBehavior = scrollBehavior,
                navigationButton = {

                    IconButton(
                        onClick = remember { { onUiAction(RecipeScreenUiAction.NavigateBack) } }
                    ) {

                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigate_back)
                        )

                    }

                },
                windowInsets = ResponsiveScaffoldUtils.topAppBarWindowInsets()
            )

        },
        floatingActionButton = {

            val backgroundColor by animateColorAsState(
                targetValue = if (screenState.recipe.isBookmarked) Theme.colors.accent
                else Theme.colors.accentContainer,
                label = ""
            )

            val contentColor by animateColorAsState(
                targetValue = if (screenState.recipe.isBookmarked) Theme.colors.onAccent
                else Theme.colors.onAccentContainer,
                label = ""
            )

            Button(
                onClick = { onUiAction(RecipeScreenUiAction.SaveRecipe(!screenState.recipe.isBookmarked)) },
                colors = ButtonDefaults.colors(
                    backgroundColor = backgroundColor,
                    contentColor = contentColor
                ),
                enabled = !screenState.isLoading
            ) {

                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.saved_icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    modifier = Modifier.animateContentSize(),
                    text = stringResource(
                        id = if (screenState.recipe.isBookmarked) {
                            R.string.saved_recipe_button_label
                        } else R.string.save_recipe_button_label
                    ),
                    style = Theme.typography.sectionTitle
                )

                Spacer(modifier = Modifier.width(2.dp))

            }

        },
        bottomBar = { NavigationBarScrim() }
    ) { paddingValues ->

        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = !screenState.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState,
                contentPadding = paddingValues
            ) {

                item(key = "top_spacer") {
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item(key = "image_item") {

                    AsyncImage(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                            .aspectRatio(16f / 10f)
                            .clip(Theme.shapes.medium)
                            .background(Theme.colors.backgroundContainer),
                        model = imageModel,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                }

                item(key = "recipe_label_item") {

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = screenState.recipe.label,
                        style = Theme.typography.sectionTitle.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                }

                item(key = "ingredients_label_item") {

                    Text(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = stringResource(id = R.string.ingredients) + ":",
                        style = Theme.typography.sectionTitle
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                }

                items(
                    items = screenState.recipe.ingredients,
                    key = { "ingredient_$it" }
                ) { ingredient ->

                    Text(
                        modifier = Modifier.padding(horizontal = 32.dp),
                        text = "\u2022   " + ingredient.text,
                        style = Theme.typography.regularText,
                        color = Theme.colors.onBackground.copy(alpha = .8f)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                }

                item(key = "procedures_label_item") {

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = stringResource(id = R.string.procedures) + ":",
                        style = Theme.typography.sectionTitle
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                }

                items(
                    items = screenState.recipe.instructions,
                    key = { "procedure_$it" }
                ) { procedure ->

                    Text(
                        modifier = Modifier.padding(horizontal = 32.dp),
                        text = "\u2022   $procedure",
                        style = Theme.typography.regularText,
                        color = Theme.colors.onBackground.copy(alpha = .8f)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                }

                if (screenState.recipe.instructions.isEmpty()) {
                    item(key = "procedures_empty_item") {

                        Text(
                            modifier = Modifier.padding(horizontal = 32.dp),
                            text = "\u2022   " + stringResource(id = R.string.no_procedures_available),
                            style = Theme.typography.regularText,
                            color = Theme.colors.onBackground.copy(alpha = .8f)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                    }
                }

                item(key = "source_link_item") {

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        onClick = {
                            onUiAction(
                                RecipeScreenUiAction.ViewFullRecipe(screenState.recipe.url)
                            )
                        },
                        colors = ButtonDefaults.colors(
                            backgroundColor = Color.Transparent,
                            contentColor = Theme.colors.primary,
                            disabledBackgroundColor = Color.Transparent
                        ),
                        enabled = !screenState.isLoading
                    ) {

                        Text(
                            modifier = Modifier.padding(horizontal = 4.dp),
                            text = stringResource(id = R.string.view_full_details),
                            style = Theme.typography.sectionTitle
                        )

                    }

                }

                item(key = "bottom_spacer") {
                    Spacer(modifier = Modifier.height(32.dp))
                    Spacer(modifier = Modifier.navigationBarsPadding())
                }

            }

        }

        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = screenState.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
                content = {

                    CircularProgressIndicator(
                        modifier = Modifier.size(72.dp),
                        strokeWidth = 4.dp,
                        color = Theme.colors.onBackground
                    )

                }
            )

        }

    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun RecipeScreenPreview() {
    FoodRecipesTheme {
        RecipeScreen(
            screenState = RecipeScreenState(recipe = RecipeModel(label = "Pancakes")),
            onUiAction = {}
        )
    }
}