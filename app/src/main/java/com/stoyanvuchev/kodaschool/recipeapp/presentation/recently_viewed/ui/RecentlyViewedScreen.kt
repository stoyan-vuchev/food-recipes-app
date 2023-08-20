package com.stoyanvuchev.kodaschool.recipeapp.presentation.recently_viewed.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.NavigationBarScrim
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
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recently_viewed.RecentlyViewedScreenState
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recently_viewed.RecentlyViewedScreenUiAction
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffoldUtils

@Composable
fun RecentlyViewedScreen(
    screenState: RecentlyViewedScreenState,
    onUiAction: (RecentlyViewedScreenUiAction) -> Unit
) {

    val layoutDirection = LocalLayoutDirection.current
    val scrollBehavior = TopBarDefaults.exitUntilCollapsedScrollBehavior()
    val categoryBarState = rememberCategoryBarState()
    val lazyGridState = rememberLazyGridState()

    BackHandler(
        enabled = screenState.category != screenState.categories.first(),
        onBack = {
            onUiAction(RecentlyViewedScreenUiAction.SetCategory(screenState.categories.first()))
        }
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

    val error by rememberUpdatedState(screenState.error.asString())

    ResponsiveScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Theme.colors.background,
        contentColor = Theme.colors.onBackground,
        topBar = {

            TopBar(
                title = stringResource(id = R.string.recently_viewed),
                navigationButton = {

                    IconButton(
                        onClick = remember { { onUiAction(RecentlyViewedScreenUiAction.GoBack) } }
                    ) {

                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigate_back)
                        )

                    }

                },
                content = {

                    CategoryBar(
                        modifier = Modifier.fadingEdges(),
                        state = categoryBarState
                    ) {

                        items(
                            items = screenState.categories,
                            key = { "category_$it" },
                            itemContent = { category ->

                                CategoryBarItemContent(
                                    selected = screenState.category == category,
                                    onSelected = remember {
                                        {
                                            onUiAction(
                                                RecentlyViewedScreenUiAction.SetCategory(category)
                                            )
                                        }
                                    },
                                    label = category.name
                                )

                            }
                        )

                    }

                },
                scrollBehavior = scrollBehavior,
                windowInsets = ResponsiveScaffoldUtils.topAppBarWindowInsets()
            )

        },
        bottomBar = { NavigationBarScrim() }
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
            columns = GridCells.Adaptive(128.dp),
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

                if (error.isEmpty()) {

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
                                        RecentlyViewedScreenUiAction.SetSaved(
                                            isSaved,
                                            recipe.recipeId
                                        )
                                    )
                                }
                            },
                            onClick = remember {
                                { onUiAction(RecentlyViewedScreenUiAction.ViewRecipe(recipe.recipeId)) }
                            }
                        )

                    }

                } else {

                    item(
                        key = "error_item",
                        span = { GridItemSpan(this.maxLineSpan) }
                    ) {

                        Text(
                            modifier = Modifier.padding(48.dp),
                            text = error,
                            style = Theme.typography.sectionTitle,
                            color = Theme.colors.error
                        )

                    }

                }

            }

        }

    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun RecentlyViewedScreenPreview() {
    FoodRecipesTheme {
        RecentlyViewedScreen(
            screenState = RecentlyViewedScreenState(),
            onUiAction = {}
        )
    }
}