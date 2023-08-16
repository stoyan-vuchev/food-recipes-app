package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.navigation_rail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button.Button
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button.ButtonDefaults
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationRailTest {

    @get:Rule
    val composeRule = createComposeRule()

    private var selectedItem = 0

    @Before
    fun setUp() {
        composeRule.setContent {
            FoodRecipesTheme {
                Box(modifier = Modifier.fillMaxSize()) {

                    NavigationRail(
                        modifier = Modifier.testTag("navigation_rail"),
                        header = {

                            Button(
                                modifier = Modifier
                                    .size(56.dp)
                                    .testTag("fab"),
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.colors(
                                    backgroundColor = Theme.colors.accentContainer,
                                    contentColor = Theme.colors.onAccentContainer
                                )
                            ) {

                                Icon(
                                    painter = painterResource(id = R.drawable.saved_icon),
                                    contentDescription = null
                                )

                            }

                        },
                        items = {

                            NavigationRailItem(
                                modifier = Modifier.testTag("nav_rail_home"),
                                selected = selectedItem == 0,
                                onSelected = { selectedItem = 0 },
                                icon = painterResource(id = R.drawable.home_icon),
                                label = "Home"
                            )

                            NavigationRailItem(
                                modifier = Modifier.testTag("nav_rail_search"),
                                selected = selectedItem == 1,
                                onSelected = { selectedItem = 1 },
                                icon = painterResource(id = R.drawable.search_icon),
                                label = "Search"
                            )

                            NavigationRailItem(
                                modifier = Modifier.testTag("nav_rail_saved"),
                                selected = selectedItem == 2,
                                onSelected = { selectedItem = 2 },
                                icon = painterResource(id = R.drawable.saved_icon),
                                label = "Saved"
                            )

                        }
                    )

                }
            }

        }
    }

    @Test
    fun assertTheNavigationRailIsDisplayed() = runTest {
        composeRule.awaitIdle()
        composeRule.onNodeWithTag("navigation_rail").assertExists()
        composeRule.onNodeWithTag("navigation_rail").assertIsDisplayed()
    }

    @Test
    fun assertTheNavigationRailHeaderIsDisplayed() = runTest {
        composeRule.awaitIdle()
        composeRule.onNodeWithTag("fab").assertExists()
        composeRule.onNodeWithTag("fab").assertIsDisplayed()
    }

    @Test
    fun assertTheNavigationRailItemsAreDisplayed() = runTest {
        composeRule.awaitIdle()
        composeRule.onNodeWithTag("nav_rail_home").assertExists()
        composeRule.onNodeWithTag("nav_rail_home").assertIsDisplayed()
        composeRule.awaitIdle()
        composeRule.onNodeWithTag("nav_rail_search").assertExists()
        composeRule.onNodeWithTag("nav_rail_search").assertIsDisplayed()
        composeRule.awaitIdle()
        composeRule.onNodeWithTag("nav_rail_saved").assertExists()
        composeRule.onNodeWithTag("nav_rail_saved").assertIsDisplayed()
    }

    @Test
    fun assertTheNavigationRailItemsCanNavigate() = runTest {
        composeRule.awaitIdle()
        composeRule.onNodeWithTag("nav_rail_search").performClick()
        composeRule.awaitIdle()
        assertThat(selectedItem).isEqualTo(1)
        composeRule.awaitIdle()
        composeRule.onNodeWithTag("nav_rail_saved").performClick()
        composeRule.awaitIdle()
        assertThat(selectedItem).isEqualTo(2)
    }

}