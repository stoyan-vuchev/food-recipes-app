package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.navigation_rail.NavigationRail
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.navigation_rail.NavigationRailItem

@Composable
fun MainActivityNavigationRail(
    navController: NavHostController,
    currentNavRoute: String?,
    navDestinationList: List<ScreenDestination>,
    visible: Boolean = navDestinationList.any { it.route == currentNavRoute }
) {

    AnimatedContent(
        modifier = Modifier.fillMaxHeight(),
        targetState = visible,
        transitionSpec = {
            slideInHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
            ) { -it } togetherWith slideOutHorizontally(
                animationSpec = spring(
                    stiffness = Spring.StiffnessMedium
                )
            ) { -it }
        },
        label = ""
    ) {

        if (it) {

            NavigationRail {

                navDestinationList.forEach { destination ->

                    NavigationRailItem(
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

}