package com.stoyanvuchev.kodaschool.recipeapp.presentation.splash

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SplashScreenUiAction {
    data object CompleteSetup : SplashScreenUiAction
}