package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.CategoryBar
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.CategoryBarItemContent
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar.rememberCategoryBarState
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory

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

                    val categories = listOf(
                        RecipesCategory.Breakfast,
                        RecipesCategory.Lunch,
                        RecipesCategory.Dinner,
                        RecipesCategory.Teatime,
                        RecipesCategory.Snack
                    )

                    var selectedCategory by remember { mutableStateOf(categories.first()) }
                    val categoryBarState = rememberCategoryBarState()

                    CategoryBar(
                        state = categoryBarState,
                        items = {

                            items(
                                items = categories,
                                key = { "category_$it" },
                                itemContent = { category ->

                                    CategoryBarItemContent(
                                        selected = selectedCategory == category,
                                        onSelected = remember {
                                            { selectedCategory = category }
                                        },
                                        label = category.name
                                    )

                                }
                            )

                        }
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