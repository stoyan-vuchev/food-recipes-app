package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.stoyanvuchev.kodaschool.recipeapp.presentation.home.HomeScreenUiAction
import com.stoyanvuchev.kodaschool.recipeapp.presentation.home.HomeScreenViewModel
import com.stoyanvuchev.kodaschool.recipeapp.presentation.home.ui.HomeScreen
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recipe.RecipeScreenUiAction
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recipe.RecipeScreenViewModel
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recipe.ui.RecipeScreen
import com.stoyanvuchev.kodaschool.recipeapp.presentation.saved.SavedRecipesScreenUiAction
import com.stoyanvuchev.kodaschool.recipeapp.presentation.saved.SavedRecipesScreenViewModel
import com.stoyanvuchev.kodaschool.recipeapp.presentation.saved.ui.SavedRecipesScreen
import kotlinx.coroutines.flow.collectLatest

fun NavGraphBuilder.mainNavigationGraph(navController: NavHostController) {

    navigation(
        startDestination = MainScreenDestinations.Home.route,
        route = MainScreenDestinations.route
    ) {

        composable(
            route = MainScreenDestinations.Home.route,
            content = {

                val viewModel = hiltViewModel<HomeScreenViewModel>()
                val screenState by viewModel.state.collectAsStateWithLifecycle()
                val categorizedRecipes by viewModel.categorizedRecipes.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = viewModel.uiActionFlow) {
                    viewModel.uiActionFlow.collectLatest { uiAction ->
                        when (uiAction) {

                            is HomeScreenUiAction.Search -> navController.navigate(
                                route = MainScreenDestinations.Search.route
                            ) { launchSingleTop = true }

                            is HomeScreenUiAction.ViewRecipe -> navController.navigate(
                                route = MainScreenDestinations.Recipe.route
                                    .replace(
                                        "{recipeId}",
                                        uiAction.recipeId
                                    )
                            ) { launchSingleTop = true }

                            else -> Unit

                        }
                    }
                }

                HomeScreen(
                    screenState = screenState,
                    recentRecipes = viewModel.recentRecipes,
                    categorizedRecipes = categorizedRecipes,
                    onUiAction = viewModel::onUiAction
                )

            }
        )

        composable(
            route = MainScreenDestinations.Search.route,
            content = {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("test_tag_search_screen"),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = MainScreenDestinations.Search.label?.asString() ?: "")

                }

            }
        )

        composable(
            route = MainScreenDestinations.Saved.route,
            content = {

                val viewModel = hiltViewModel<SavedRecipesScreenViewModel>()
                val screenState by viewModel.screenState.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = viewModel.uiActionFlow) {
                    viewModel.uiActionFlow.collectLatest { uiAction ->
                        when (uiAction) {

                            is SavedRecipesScreenUiAction.ViewRecipe -> navController.navigate(
                                route = MainScreenDestinations.Recipe.route
                                    .replace(
                                        "{recipeId}",
                                        uiAction.recipeId
                                    )
                            ) { launchSingleTop = true }

                            else -> Unit

                        }
                    }
                }

                SavedRecipesScreen(
                    screenState = screenState,
                    onUiAction = viewModel::onUiAction
                )

            }
        )

        composable(
            route = MainScreenDestinations.Recipe.route,
            arguments = listOf(
                navArgument(
                    name = "recipeId",
                    builder = { type = NavType.StringType }
                )
            ),
            content = {

                val viewModel = hiltViewModel<RecipeScreenViewModel>()
                val screenState by viewModel.screenState.collectAsStateWithLifecycle()
                val uriHandler = LocalUriHandler.current

                LaunchedEffect(key1 = viewModel.uiActionFlow) {
                    viewModel.uiActionFlow.collectLatest { uiAction ->
                        when (uiAction) {
                            is RecipeScreenUiAction.NavigateBack -> navController.navigateUp()
                            is RecipeScreenUiAction.ViewFullRecipe -> uriHandler.openUri(uiAction.url)
                            else -> Unit
                        }
                    }
                }

                RecipeScreen(
                    screenState = screenState,
                    onUiAction = viewModel::onUiAction
                )

            }
        )

    }

}