package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
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

    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            FoodRecipesTheme { MainActivityNavHost(navController = navController) }
        }
    }

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