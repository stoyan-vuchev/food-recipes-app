package com.stoyanvuchev.kodaschool.recipeapp.presentation.home.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.LocalPaddingValues
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button.Button
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button.ButtonDefaults
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.CategoryBar
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.CategoryBarItemContent
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.rememberCategoryBarState
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.empty_states.EmptyStateText
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.fadingEdges
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.recipe_grid_item.RecipeGridItem
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.recipe_grid_item.RecipeGridItemLoadingShimmer
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.search_bar.SearchBar
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.search_bar.SearchBarMode
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.topbar.TopBar
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.topbar.TopBarDefaults
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeModel
import com.stoyanvuchev.kodaschool.recipeapp.presentation.home.HomeScreenState
import com.stoyanvuchev.kodaschool.recipeapp.presentation.home.HomeScreenUiAction
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffoldUtils

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    screenState: HomeScreenState,
    recentRecipes: List<RecipeModel>,
    categorizedRecipes: List<RecipeModel>,
    onUiAction: (HomeScreenUiAction) -> Unit
) {

    val lazyListState = rememberLazyListState()
    val categoryBarState = rememberCategoryBarState()
    val recentLazyListState = rememberLazyListState()
    val categoryLazyListState = rememberLazyListState()
    val scrollBehavior = TopBarDefaults.exitUntilCollapsedScrollBehavior()

    BackHandler(
        enabled = screenState.category != screenState.categories.first(),
        onBack = { onUiAction(HomeScreenUiAction.SetCategory(screenState.categories.first())) }
    )

    LaunchedEffect(key1 = recentRecipes) {
        recentLazyListState.animateScrollToItem(0, 0)
    }

    LaunchedEffect(key1 = screenState.category) {
        categoryBarState.animateScrollToItem(
            if (screenState.categories.indexOf(screenState.category) > 2) {
                screenState.categories.size - 1
            } else 0, 0
        )
    }

    LaunchedEffect(key1 = screenState.isLoadingCategory) {
        categoryLazyListState.animateScrollToItem(0, 0)
    }

    val searchBarTopSpacerHeight by remember(scrollBehavior.state) {
        derivedStateOf {
            lerp(
                start = 24.dp,
                stop = 0.dp,
                fraction = scrollBehavior.state.collapsedFraction
            )
        }
    }

    val localPadding = LocalPaddingValues.current
    val layoutDirection = LocalLayoutDirection.current
    val absolutePadding by rememberUpdatedState(
        PaddingValues(
            top = 0.dp,
            start = localPadding.calculateStartPadding(layoutDirection),
            end = localPadding.calculateEndPadding(layoutDirection),
            bottom = localPadding.calculateBottomPadding()
        )
    )

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
                title = stringResource(id = R.string.home_screen_top_bar_title),
                content = {

                    Spacer(modifier = Modifier.height(searchBarTopSpacerHeight))

                    SearchBar(
                        queryText = "",
                        onQueryText = remember { {} },
                        queryHint = stringResource(id = R.string.search_query_hint),
                        onClickOrImeAction = remember {
                            { onUiAction(HomeScreenUiAction.Search) }
                        },
                        mode = SearchBarMode.Navigate
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                },
                scrollBehavior = scrollBehavior,
                windowInsets = ResponsiveScaffoldUtils.topAppBarWindowInsets()
            )

        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("test_tag_home_screen"),
            state = lazyListState,
            contentPadding = paddingValues
        ) {

            item(key = "top_spacer") {
                Spacer(modifier = Modifier.height(24.dp))
            }

            item(key = "category_item") {

                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = stringResource(id = R.string.category),
                    style = Theme.typography.sectionTitle.copy(fontWeight = FontWeight.Bold),
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
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    userScrollEnabled = !screenState.isLoadingCategory
                ) {


                    if (screenState.isLoadingCategory) {

                        items(
                            count = 20,
                            key = { "item_$it" },
                            itemContent = {

                                RecipeGridItemLoadingShimmer(
                                    modifier = Modifier
                                        .width(128.dp)
                                        .animateItemPlacement()
                                )

                            }
                        )

                    } else {

                        if (categorizedRecipes.isEmpty()) {

                            item(key = "empty_state_list_item") {

                                EmptyStateText(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 56.dp),
                                    text = stringResource(id = R.string.home_screen_category_list_empty_state_text)
                                )

                            }

                        }

                        items(
                            items = categorizedRecipes,
                            key = { "recipe_${it.recipeId}" }
                        ) { recipe ->

                            RecipeGridItem(
                                modifier = Modifier
                                    .width(128.dp)
                                    .animateItemPlacement(),
                                recipe = recipe,
                                enabled = true,
                                onSave = {
                                    onUiAction(
                                        HomeScreenUiAction.SaveOrRemoveRecipe(
                                            recipeId = recipe.recipeId,
                                            saved = !recipe.isBookmarked
                                        )
                                    )
                                },
                                onClick = {
                                    onUiAction(
                                        HomeScreenUiAction.ViewRecipe(recipeId = recipe.recipeId)
                                    )
                                }
                            )

                        }

                    }

                }

            }

            item(key = "recently_viewed_item") {

                AnimatedContent(
                    targetState = recentRecipes.isNotEmpty(),
                    label = ""
                ) { visible ->

                    if (visible) {

                        Column {

                            Spacer(modifier = Modifier.height(40.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 24.dp),
                                    text = stringResource(id = R.string.recently_viewed),
                                    style = Theme.typography.sectionTitle.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = Theme.colors.onBackground
                                )

                                Button(
                                    onClick = remember {
                                        { onUiAction(HomeScreenUiAction.SeeAllRecentlyViewed) }
                                    },
                                    colors = ButtonDefaults.colors(
                                        backgroundColor = Color.Transparent,
                                        contentColor = Theme.colors.accent
                                    )
                                ) {

                                    Spacer(modifier = Modifier.width(2.dp))

                                    Text(
                                        text = stringResource(id = R.string.see_all),
                                        style = Theme.typography.regularText.copy(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Icon(
                                        modifier = Modifier.size(16.dp),
                                        imageVector = Icons.Rounded.ArrowForward,
                                        contentDescription = null
                                    )

                                }

                                Spacer(modifier = Modifier.width(16.dp))

                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            LazyRow(
                                modifier = Modifier.fadingEdges(),
                                state = recentLazyListState,
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                contentPadding = PaddingValues(horizontal = 24.dp)
                            ) {

                                items(
                                    items = recentRecipes,
                                    key = { "recent_recipe_${it.recipeId}" }
                                ) { recipe ->

                                    RecipeGridItem(
                                        modifier = Modifier
                                            .width(128.dp)
                                            .animateItemPlacement(),
                                        recipe = recipe,
                                        enabled = true,
                                        onSave = {
                                            onUiAction(
                                                HomeScreenUiAction.SaveOrRemoveRecipe(
                                                    recipeId = recipe.recipeId,
                                                    saved = !recipe.isBookmarked
                                                )
                                            )
                                        },
                                        onClick = {
                                            onUiAction(
                                                HomeScreenUiAction.ViewRecipe(recipeId = recipe.recipeId)
                                            )
                                        }
                                    )

                                }

                            }

                        }

                    }

                }
            }

            item(key = "bottom_spacer") {
                Spacer(modifier = Modifier.height(32.dp))
            }

        }

    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    FoodRecipesTheme {
        HomeScreen(
            screenState = HomeScreenState(),
            recentRecipes = listOf(
                RecipeModel(recipeId = "0"),
                RecipeModel(recipeId = "1"),
                RecipeModel(recipeId = "2"),
                RecipeModel(recipeId = "3"),
                RecipeModel(recipeId = "4"),
            ),
            categorizedRecipes = emptyList(),
            onUiAction = {}
        )
    }
}