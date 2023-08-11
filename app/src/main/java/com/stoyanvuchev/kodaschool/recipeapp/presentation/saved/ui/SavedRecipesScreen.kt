package com.stoyanvuchev.kodaschool.recipeapp.presentation.saved.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens
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

@Composable
fun SavedRecipesScreen(
    screenState: SavedRecipesScreenState,
    onUiAction: (SavedRecipesScreenUiAction) -> Unit
) {

    val layoutDirection = LocalLayoutDirection.current
    val lazyGridState = rememberLazyGridState()
    val categoryBarState = rememberCategoryBarState()
    val scrollBehavior = TopBarDefaults.exitUntilCollapsedScrollBehavior()

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

    val collapsedTopBarFraction by remember(scrollBehavior.state) {
        derivedStateOf {
            scrollBehavior.state.collapsedFraction
        }
    }

    val isSectionLabelVisible by remember(collapsedTopBarFraction) {
        derivedStateOf {
            collapsedTopBarFraction <= 0.5f
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Theme.colors.background,
        contentColor = Theme.colors.onBackground,
        topBar = {

            TopBar(
                title = stringResource(id = R.string.saved_screen_title),
                scrollBehavior = scrollBehavior
            )

        }
    ) { paddingValues ->

        val topPadding by rememberUpdatedState(
            PaddingValues(
                top = paddingValues.calculateTopPadding()
            )
        )

        val actualPadding by rememberUpdatedState(
            PaddingValues(
                start = paddingValues.calculateStartPadding(layoutDirection) + 12.dp,
                end = paddingValues.calculateEndPadding(layoutDirection) + 12.dp,
                bottom = paddingValues.calculateBottomPadding() + 128.dp,
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPadding.calculateTopPadding())
        ) {

            AnimatedContent(
                targetState = isSectionLabelVisible,
                label = ""
            ) { isVisible ->

                if (isVisible) {

                    Column {

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            modifier = Modifier.padding(horizontal = 24.dp),
                            text = "Filter by:",
                            style = Theme.typography.sectionTitle.copy(fontWeight = FontWeight.Bold),
                            color = Theme.colors.onBackground
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }

                }

            }

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
                                    { onUiAction(SavedRecipesScreenUiAction.SetCategory(category)) }
                                },
                                label = category.name
                            )

                        }
                    )

                }
            )

            Divider(
                color = ComponentsTokens.Separator.color.copy(alpha = collapsedTopBarFraction),
                thickness = ComponentsTokens.Separator.thickness
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