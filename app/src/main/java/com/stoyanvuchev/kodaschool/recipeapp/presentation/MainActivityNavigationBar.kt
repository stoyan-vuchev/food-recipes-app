package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.navigation_bar.NavigationBar
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.navigation_bar.NavigationBarItem

@Composable
fun MainActivityNavigationBar(
    navController: NavHostController,
    currentNavRoute: String?,
    navDestinationList: List<ScreenDestination>,
    visible: Boolean = navDestinationList.any { it.route == currentNavRoute }
) {

    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth(),
        visible = visible,
        enter = slideInVertically(
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
        ) { it },
        exit = slideOutVertically(
            animationSpec = spring(
                stiffness = Spring.StiffnessMedium
            )
        ) { it }
    ) {

        NavigationBar {

            navDestinationList.forEach { destination ->

                NavigationBarItem(
                    selected = destination.route == currentNavRoute,
                    onSelected = remember {
                        {
                            navController.navigate(route = destination.route) {

                                popUpTo(route = navDestinationList.first().route) {
                                    saveState = true
                                    inclusive = false
                                }

                                launchSingleTop = true
                                restoreState = true

                            }
                        }
                    },
                    icon = painterResource(id = destination.icon!!),
                    label = destination.label!!.asString()
                )

            }

        }

    }

}