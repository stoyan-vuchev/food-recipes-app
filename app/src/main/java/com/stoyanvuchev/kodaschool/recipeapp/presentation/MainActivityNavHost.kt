package com.stoyanvuchev.kodaschool.recipeapp.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainActivityNavHost(navController: NavHostController) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentColor = Theme.colors.onBackground,
        bottomBar = {

            MainActivityNavigationBar(
                navController = navController,
                currentNavRoute = backStackEntry?.destination?.route,
                navDestinationList = MainScreenDestinations.navigationBarDestinations
            )

        }
    ) {

        AnimatedNavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = MainScreenDestinations.route,
            builder = { mainNavigationGraph(navController = navController) }
        )

    }

}