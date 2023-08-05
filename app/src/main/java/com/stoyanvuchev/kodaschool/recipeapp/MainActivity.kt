package com.stoyanvuchev.kodaschool.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.presentation.MainActivityNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navController = rememberAnimatedNavController()
            FoodRecipesTheme { MainActivityNavHost(navController = navController) }
        }
    }

}