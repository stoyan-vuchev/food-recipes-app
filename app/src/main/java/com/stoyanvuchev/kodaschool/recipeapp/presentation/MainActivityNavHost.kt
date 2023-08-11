package com.stoyanvuchev.kodaschool.recipeapp.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainActivityNavHost(navController: NavHostController) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Theme.colors.background,
        contentColor = Theme.colors.onBackground,
        bottomBar = {

            MainActivityNavigationBar(
                navController = navController,
                currentNavRoute = backStackEntry?.destination?.route,
                navDestinationList = MainScreenDestinations.navigationBarDestinations
            )

        }
    ) {

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