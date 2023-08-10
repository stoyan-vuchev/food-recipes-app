package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("test_tag_saved_screen"),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = MainScreenDestinations.Saved.label?.asString() ?: "")
                }

            }
        )

        composable(
            route = MainScreenDestinations.Recipe.route,
            arguments = listOf(
                navArgument(name = "recipeId") { type = NavType.StringType }
            ),
            content = {

                Text(text = it.arguments?.getString("recipeId") ?: "Error")

            }
        )

    }

}