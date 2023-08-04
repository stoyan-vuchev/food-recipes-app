package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainActivityNavHost(navController: NavHostController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        AnimatedNavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = MainScreenDestinations.route,
            builder = { mainNavigationGraph(navController = navController) }
        )

    }

}