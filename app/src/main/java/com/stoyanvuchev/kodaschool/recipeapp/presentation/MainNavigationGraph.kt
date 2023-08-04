package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainNavigationGraph(navController: NavHostController) {

    navigation(
        startDestination = MainScreenDestinations.Home.route,
        route = MainScreenDestinations.route
    ) {

        composable(
            route = MainScreenDestinations.Home.route,
            content = {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("test_tag_home_screen"),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = MainScreenDestinations.Home.label?.asString() ?: "")

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        modifier = Modifier.testTag("test_tag_go_to_search"),
                        onClick = remember {
                            {
                                navController.navigate(
                                    route = MainScreenDestinations.Search.route
                                ) { launchSingleTop = true }
                            }
                        },
                        content = { Text(text = "Go to Search") }
                    )

                }

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
            content = { Text(text = MainScreenDestinations.Recipe.label?.asString() ?: "") }
        )

    }

}