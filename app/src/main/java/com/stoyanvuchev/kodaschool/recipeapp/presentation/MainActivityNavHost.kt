package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ProvidePaddingValues
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold

@Composable
fun MainActivityNavHost(navController: NavHostController) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    ResponsiveScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Theme.colors.background,
        contentColor = Theme.colors.onBackground,
        bottomBar = {

            MainActivityNavigationBar(
                navController = navController,
                currentNavRoute = backStackEntry?.destination?.route,
                navDestinationList = MainScreenDestinations.navigationBarDestinations
            )

        },
        sideRail = {

            MainActivityNavigationRail(
                navController = navController,
                currentNavRoute = backStackEntry?.destination?.route,
                navDestinationList = MainScreenDestinations.navigationBarDestinations
            )

        }
    ) { paddingValues ->

        ProvidePaddingValues(paddingValues = paddingValues) {

            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = MainScreenDestinations.route,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() },
                builder = { mainNavigationGraph(navController = navController) }
            )

        }

    }

}