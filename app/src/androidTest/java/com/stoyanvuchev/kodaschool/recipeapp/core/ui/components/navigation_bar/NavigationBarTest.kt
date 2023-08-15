package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.navigation_bar

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import com.stoyanvuchev.kodaschool.recipeapp.presentation.MainScreenDestinations
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var screenDestinations = listOf(
        MainScreenDestinations.Home,
        MainScreenDestinations.Search,
        MainScreenDestinations.Saved,
    )

    private var currentScreenDestination = screenDestinations.first()

    @Before
    fun setUp() = runTest {
        composeTestRule.setContent {
            FoodRecipesTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Theme.colors.background,
                    contentColor = Theme.colors.onBackground,
                    bottomBar = {

                        NavigationBar(
                            modifier = Modifier.testTag("navigation_bar")
                        ) {

                            screenDestinations.forEach { screenDestination ->

                                NavigationBarItem(
                                    selected = screenDestination == currentScreenDestination,
                                    onSelected = remember {
                                        { currentScreenDestination = screenDestination }
                                    },
                                    icon = painterResource(id = screenDestination.icon!!),
                                    label = screenDestination.label!!.asString()
                                )

                            }

                        }

                    }
                ) { paddingValues ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = currentScreenDestination.label!!.asString(),
                            style = Theme.typography.largeTitle
                        )

                    }

                }

            }
        }
    }

    @Test
    fun testNavigationBarBehaviors() = runTest {
        composeTestRule.awaitIdle()
        assertTheNavigationBarIsDisplayed()
        assertTheScreenDestinationIsSearch()
        assertTheScreenDestinationIsSaved()
        assertTheScreenDestinationIsHome()
    }

    private fun assertTheNavigationBarIsDisplayed() = runTest {
        composeTestRule.onNodeWithTag("navigation_bar").assertIsDisplayed()
        composeTestRule.awaitIdle()
    }

    private fun assertTheScreenDestinationIsSearch() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeTestRule.onAllNodesWithText(screenDestinations[1].label!!.asString(context))[0].performClick()
        composeTestRule.awaitIdle()
    }

    private fun assertTheScreenDestinationIsSaved() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeTestRule.onAllNodesWithText(screenDestinations[2].label!!.asString(context))[0].performClick()
        composeTestRule.awaitIdle()
    }

    private fun assertTheScreenDestinationIsHome() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeTestRule.onAllNodesWithText(screenDestinations[0].label!!.asString(context))[0].performClick()
        composeTestRule.awaitIdle()
    }

}