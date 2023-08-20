package com.stoyanvuchev.kodaschool.recipeapp.presentation.saved.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.LocalPaddingValues
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.CategoryBar
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.CategoryBarItemContent
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.rememberCategoryBarState
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.fadingEdges
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.recipe_grid_item.RecipeGridItem
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.recipe_grid_item.RecipeGridItemLoadingShimmer
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.topbar.TopBar
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.topbar.TopBarDefaults
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import com.stoyanvuchev.kodaschool.recipeapp.presentation.saved.SavedRecipesScreenState
import com.stoyanvuchev.kodaschool.recipeapp.presentation.saved.SavedRecipesScreenUiAction
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffoldUtils

@Composable
fun SavedRecipesScreen(
    screenState: SavedRecipesScreenState,
    onUiAction: (SavedRecipesScreenUiAction) -> Unit
) {

    val layoutDirection = LocalLayoutDirection.current
    val lazyGridState = rememberLazyGridState()
    val categoryBarState = rememberCategoryBarState()
    val scrollBehavior = TopBarDefaults.exitUntilCollapsedScrollBehavior()

    val localPadding = LocalPaddingValues.current
    val absolutePadding by rememberUpdatedState(
        PaddingValues(
            top = 0.dp,
            start = localPadding.calculateStartPadding(layoutDirection),
            end = localPadding.calculateEndPadding(layoutDirection),
            bottom = localPadding.calculateBottomPadding()
        )
    )

    BackHandler(
        enabled = screenState.category != screenState.categories.first(),
        onBack = { onUiAction(SavedRecipesScreenUiAction.SetCategory(screenState.categories.first())) }
    )

    LaunchedEffect(key1 = screenState.category) {
        categoryBarState.animateScrollToItem(
            if (screenState.categories.indexOf(screenState.category) > 2) {
                screenState.categories.size - 1
            } else 0, 0
        )
    }

    LaunchedEffect(key1 = screenState.isLoading) {
        lazyGridState.animateScrollToItem(0, 0)
    }

    ResponsiveScaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .padding(absolutePadding),
        containerColor = Theme.colors.background,
        contentColor = Theme.colors.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {

            TopBar(
                title = stringResource(id = R.string.saved_screen_title),
                content = {

                    CategoryBar(
                        modifier = Modifier.fadingEdges(),
                        state = categoryBarState,
                        horizontalContentPadding = 24.dp,
                        items = {

                            items(
                                items = screenState.categories,
                                key = { "category_$it" },
                                itemContent = { category ->

                                    CategoryBarItemContent(
                                        selected = screenState.category == category,
                                        onSelected = remember {
                                            {
                                                onUiAction(
                                                    SavedRecipesScreenUiAction.SetCategory(
                                                        category
                                                    )
                                                )
                                            }
                                        },
                                        label = category.name
                                    )

                                }
                            )

                        }
                    )

                },
                scrollBehavior = scrollBehavior,
                windowInsets = ResponsiveScaffoldUtils.topAppBarWindowInsets()
            )

        }
    ) { paddingValues ->

        val actualPadding by rememberUpdatedState(
            PaddingValues(
                top = paddingValues.calculateTopPadding() + 12.dp,
                start = paddingValues.calculateStartPadding(layoutDirection) + 12.dp,
                end = paddingValues.calculateEndPadding(layoutDirection) + 12.dp,
                bottom = paddingValues.calculateBottomPadding() + 128.dp,
            )
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            state = lazyGridState,
            columns = GridCells.Adaptive(150.dp),
            contentPadding = actualPadding,
            userScrollEnabled = !screenState.isLoading
        ) {

            if (screenState.isLoading) {

                items(
                    count = 20,
                    key = { "saved_recipe_placeholder_$it" }
                ) {

                    RecipeGridItemLoadingShimmer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )

                }

            } else {

                items(
                    items = screenState.recipes,
                    key = { "saved_recipe_$it" }
                ) { recipe ->

                    var isSaved by remember { mutableStateOf(recipe.isBookmarked) }

                    RecipeGridItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        recipe = recipe.copy(isBookmarked = isSaved),
                        enabled = true,
                        onSave = remember {
                            {
                                isSaved = !isSaved
                                onUiAction(
                                    SavedRecipesScreenUiAction.SetSaved(
                                        isSaved,
                                        recipe.recipeId
                                    )
                                )
                            }
                        },
                        onClick = remember {
                            { onUiAction(SavedRecipesScreenUiAction.ViewRecipe(recipe.recipeId)) }
                        }
                    )

                }

            }

        }

    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SavedRecipesScreenPreview() {
    FoodRecipesTheme {
        SavedRecipesScreen(
            screenState = SavedRecipesScreenState(),
            onUiAction = {}
        )
    }
}