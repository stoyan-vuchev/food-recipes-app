package com.stoyanvuchev.kodaschool.recipeapp.presentation.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.LocalPaddingValues
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
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import com.stoyanvuchev.kodaschool.recipeapp.presentation.search.SearchScreenState
import com.stoyanvuchev.kodaschool.recipeapp.presentation.search.SearchScreenUiAction
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffoldUtils
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    screenState: SearchScreenState,
    onUiAction: (SearchScreenUiAction) -> Unit
) {

    val scrollBehavior = TopBarDefaults.exitUntilCollapsedScrollBehavior()
    val categoryBarState = rememberCategoryBarState()
    val lazyGridState = rememberLazyGridState()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

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

    val searchBarTopSpacerHeight by remember(scrollBehavior.state) {
        derivedStateOf {
            lerp(
                start = 24.dp,
                stop = 0.dp,
                fraction = scrollBehavior.state.collapsedFraction
            )
        }
    }

    LaunchedEffect(key1 = screenState.searchResults) {
        lazyGridState.animateScrollToItem(0, 0)
    }

    LaunchedEffect(key1 = screenState.category) {
        categoryBarState.animateScrollToItem(
            if (screenState.categories.indexOf(screenState.category) > 2) {
                screenState.categories.size - 1
            } else 0, 0
        )
    }

    LaunchedEffect(key1 = Unit) {
        delay(512L)
        focusManager.moveFocus(FocusDirection.Up)
        keyboardController?.show()
    }

    ResponsiveScaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("test_tag_search_screen")
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .padding(absolutePadding),
        containerColor = Theme.colors.background,
        contentColor = Theme.colors.onBackground,
        topBar = {

            TopBar(
                title = stringResource(id = R.string.search_screen_top_bar_title),
                content = {

                    Spacer(modifier = Modifier.height(searchBarTopSpacerHeight))

                    SearchBar(
                        queryText = screenState.searchQueryText,
                        onQueryText = remember {
                            { onUiAction(SearchScreenUiAction.SetSearchQueryText(it)) }
                        },
                        queryHint = stringResource(id = R.string.search_query_hint),
                        onClickOrImeAction = remember {
                            {
                                keyboardController?.hide()
                                focusManager.clearFocus()
                                onUiAction(SearchScreenUiAction.Search)
                            }
                        },
                        mode = SearchBarMode.Search
                    )

                    Spacer(modifier = Modifier.height(16.dp))

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
                                                onUiAction(SearchScreenUiAction.SetCategory(category))
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
                bottom = paddingValues.calculateBottomPadding() + 12.dp,
            )
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            state = lazyGridState,
            columns = GridCells.Adaptive(150.dp),
            contentPadding = actualPadding,
            userScrollEnabled = !screenState.isSearching
        ) {

            if (screenState.isSearching) {

                items(
                    count = 20,
                    key = { "search_result_recipe_placeholder_$it" }
                ) {

                    RecipeGridItemLoadingShimmer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        isSaveButtonVisible = false
                    )

                }

            } else {

                if (screenState.searchResults.isEmpty() && screenState.isSearchComplete) {

                    item(
                        key = "empty_state_list_item",
                        span = { GridItemSpan(maxLineSpan) }
                    ) {

                        EmptyStateText(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 128.dp),
                            text = stringResource(id = R.string.search_screen_list_empty_state_text)
                        )

                    }

                }

                items(
                    items = screenState.searchResults,
                    key = { "search_result_recipe_$it" }
                ) { recipe ->

                    RecipeGridItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        recipe = recipe,
                        enabled = true,
                        isSaveButtonVisible = false,
                        onSave = remember { {} },
                        onClick = remember {
                            { onUiAction(SearchScreenUiAction.ViewRecipe(recipe.recipeId)) }
                        }
                    )

                }

            }

        }

    }

}