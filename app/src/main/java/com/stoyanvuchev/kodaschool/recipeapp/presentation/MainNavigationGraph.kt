package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
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
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recently_viewed.RecentlyViewedScreenUiAction
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recently_viewed.RecentlyViewedScreenViewModel
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recently_viewed.ui.RecentlyViewedScreen
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recipe.RecipeScreenUiAction
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recipe.RecipeScreenViewModel
import com.stoyanvuchev.kodaschool.recipeapp.presentation.recipe.ui.RecipeScreen
import com.stoyanvuchev.kodaschool.recipeapp.presentation.saved.SavedRecipesScreenUiAction
import com.stoyanvuchev.kodaschool.recipeapp.presentation.saved.SavedRecipesScreenViewModel
import com.stoyanvuchev.kodaschool.recipeapp.presentation.saved.ui.SavedRecipesScreen
import com.stoyanvuchev.kodaschool.recipeapp.presentation.search.SearchScreenUiAction
import com.stoyanvuchev.kodaschool.recipeapp.presentation.search.SearchScreenViewModel
import com.stoyanvuchev.kodaschool.recipeapp.presentation.search.ui.SearchScreen
import com.stoyanvuchev.kodaschool.recipeapp.presentation.splash.SplashScreenUiAction
import com.stoyanvuchev.kodaschool.recipeapp.presentation.splash.SplashScreenViewModel
import com.stoyanvuchev.kodaschool.recipeapp.presentation.splash.ui.SplashScreen
import kotlinx.coroutines.flow.collectLatest

fun NavGraphBuilder.mainNavigationGraph(navController: NavHostController) {

    navigation(
        startDestination = MainScreenDestinations.Splash.route,
        route = MainScreenDestinations.route
    ) {

        composable(
            route = MainScreenDestinations.Splash.route,
            content = {

                val viewModel = hiltViewModel<SplashScreenViewModel>()
                val isSetupCompleted by viewModel.isSetupCompleted.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = viewModel.uiActionFlow) {
                    viewModel.uiActionFlow.collectLatest { uiAction ->
                        when (uiAction) {
                            is SplashScreenUiAction.CompleteSetup -> navController.navigate(
                                MainScreenDestinations.Home.route
                            ) {
                                popUpTo(MainScreenDestinations.Splash.route) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    }
                }

                SplashScreen(
                    isSetupCompleted = isSetupCompleted,
                    onUiAction = viewModel::onUiAction
                )

            }
        )

        composable(
            route = MainScreenDestinations.Home.route,
            content = {

                val viewModel = hiltViewModel<HomeScreenViewModel>()
                val screenState by viewModel.state.collectAsStateWithLifecycle()
                val recentRecipes by viewModel.recentRecipes.collectAsStateWithLifecycle()
                val categorizedRecipes by viewModel.categorizedRecipes.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = viewModel.uiActionFlow) {
                    viewModel.uiActionFlow.collectLatest { uiAction ->
                        when (uiAction) {

                            is HomeScreenUiAction.Search -> navController.navigate(
                                route = MainScreenDestinations.Search.route
                            ) { launchSingleTop = true }

                            is HomeScreenUiAction.SeeAllRecentlyViewed -> navController.navigate(
                                route = MainScreenDestinations.RecentlyViewed.route
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
                    recentRecipes = recentRecipes,
                    categorizedRecipes = categorizedRecipes,
                    onUiAction = viewModel::onUiAction
                )

            }
        )

        composable(
            route = MainScreenDestinations.Search.route,
            content = {

                val viewModel = hiltViewModel<SearchScreenViewModel>()
                val screenState by viewModel.screenState.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = viewModel.uiActionFlow) {
                    viewModel.uiActionFlow.collectLatest { uiAction ->
                        when (uiAction) {

                            is SearchScreenUiAction.ViewRecipe -> navController.navigate(
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

                SearchScreen(
                    screenState = screenState,
                    onUiAction = viewModel::onUiAction
                )

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

        composable(
            route = MainScreenDestinations.RecentlyViewed.route,
            content = {

                val viewModel = hiltViewModel<RecentlyViewedScreenViewModel>()
                val screenState by viewModel.state.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = viewModel.uiActionFlow) {
                    viewModel.uiActionFlow.collectLatest { uiAction ->
                        when (uiAction) {

                            is RecentlyViewedScreenUiAction.GoBack -> navController.navigateUp()

                            is RecentlyViewedScreenUiAction.ViewRecipe -> navController.navigate(
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

                RecentlyViewedScreen(
                    screenState = screenState,
                    onUiAction = viewModel::onUiAction
                )

            }
        )

    }

}