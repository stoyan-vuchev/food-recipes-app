package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.accompanist.navigation.animation.AnimatedComposeNavigator
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityNavHostTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    // Setup the TestNavHostController and set MainActivityNavHost as the content.
    @OptIn(ExperimentalAnimationApi::class)
    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(AnimatedComposeNavigator())
            FoodRecipesTheme { MainActivityNavHost(navController = navController) }
        }
    }

    // Verify whether the Home screen is displayed.
    @Test
    fun verifyStartDestinationIsDisplayed() = runTest {
        composeTestRule.onNodeWithTag("test_tag_home_screen").assertIsDisplayed()
    }

//    @Test
//    fun verifyNavigateToSearchScreen() = navigateToScreenAndAssertItMatches(
//        testTag = "test_tag_go_to_search",
//        expectedRoute = MainScreenDestinations.Search.route
//    )
//
//    @Test
//    fun verifyNavigateToSavedScreen() = navigateToScreenAndAssertItMatches(
//        testTag = "test_tag_go_to_saved",
//        expectedRoute = MainScreenDestinations.Saved.route
//    )
//
//    private fun navigateToScreenAndAssertItMatches(
//        testTag: String,
//        expectedRoute: String
//    ) = runTest {
//
//        composeTestRule.onNodeWithTag(testTag).performClick()
//        composeTestRule.awaitIdle()
//
//        val actualRoute = navController.currentDestination?.route
//        assertThat(actualRoute).isEqualTo(expectedRoute)
//
//    }

}