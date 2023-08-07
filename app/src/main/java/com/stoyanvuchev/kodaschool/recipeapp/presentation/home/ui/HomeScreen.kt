package com.stoyanvuchev.kodaschool.recipeapp.presentation.home.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.CategoryBar
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.CategoryBarItemContent
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.rememberCategoryBarState
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.fadingEdges
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.recipe_grid_item.RecipeGridItem
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.recipe_grid_item.RecipeGridItemLoadingShimmer
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel
import com.stoyanvuchev.kodaschool.recipeapp.presentation.home.HomeScreenState
import com.stoyanvuchev.kodaschool.recipeapp.presentation.home.HomeScreenUiAction

@Composable
fun HomeScreen(
    screenState: HomeScreenState,
    onUiAction: (HomeScreenUiAction) -> Unit
) {

    val categoryBarState = rememberCategoryBarState()
    val categoryLazyListState = rememberLazyListState()

    BackHandler(
        enabled = screenState.category != screenState.categories.first(),
        onBack = { onUiAction(HomeScreenUiAction.SetCategory(screenState.categories.first())) }
    )

    LaunchedEffect(key1 = screenState.category) {
        categoryBarState.animateScrollToItem(
            screenState.categories.indexOf(screenState.category), 0
        )
    }

    LaunchedEffect(key1 = screenState.isLoadingCategory) {
        categoryLazyListState.animateScrollToItem(0, 0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("test_tag_home_screen")
    ) {

        Spacer(modifier = Modifier.statusBarsPadding())

        Spacer(modifier = Modifier.height(64.dp))

        Text(
            modifier = Modifier.padding(horizontal = 32.dp),
            text = "Category",
            style = Theme.typography.sectionTitle,
            color = Theme.colors.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        CategoryBar(
            modifier = Modifier.fadingEdges(),
            state = categoryBarState,
            items = {

                items(
                    items = screenState.categories,
                    key = { "category_$it" },
                    itemContent = { category ->

                        CategoryBarItemContent(
                            selected = screenState.category == category,
                            onSelected = remember {
                                { onUiAction(HomeScreenUiAction.SetCategory(category)) }
                            },
                            label = category.name
                        )

                    }
                )

            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            modifier = Modifier.fadingEdges(),
            state = categoryLazyListState,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 32.dp),
            userScrollEnabled = !screenState.isLoadingCategory
        ) {

            if (screenState.isLoadingCategory) {

                items(
                    count = 5,
                    key = { "item_$it" },
                    itemContent = {

                        RecipeGridItemLoadingShimmer(modifier = Modifier.fillMaxWidth())

                    }
                )

            } else {

                items(
                    items = screenState.categoryRecipesList,
                    key = { "recipe_$it" }
                ) { recipe ->

                    RecipeGridItem(
                        modifier = Modifier.fillMaxWidth(),
                        recipe = recipe,
                        enabled = true,
                        onSave = remember {
                            {
                                onUiAction(
                                    HomeScreenUiAction.SaveOrRemoveRecipe(
                                        recipeId = recipe.recipeId,
                                        saved = recipe.isBookmarked
                                    )
                                )
                            }
                        },
                        onClick = remember {
                            {
                                onUiAction(
                                    HomeScreenUiAction.ViewRecipe(recipeId = recipe.recipeId)
                                )
                            }
                        }
                    )

                }

            }

        }

    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    FoodRecipesTheme {
        HomeScreen(
            screenState = HomeScreenState(
                categoryRecipesList = listOf(
                    RecipeModel(recipeId = "0"),
                    RecipeModel(recipeId = "1"),
                    RecipeModel(recipeId = "2"),
                    RecipeModel(recipeId = "3"),
                    RecipeModel(recipeId = "4"),
                )
            ),
            onUiAction = {}
        )
    }
}