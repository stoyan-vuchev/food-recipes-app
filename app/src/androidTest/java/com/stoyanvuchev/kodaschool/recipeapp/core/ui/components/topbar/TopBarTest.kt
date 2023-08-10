package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TopBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            FoodRecipesTheme {

                val scrollBehavior = TopBarDefaults.exitUntilCollapsedScrollBehavior()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    containerColor = Theme.colors.background,
                    contentColor = Theme.colors.onBackground,
                    topBar = {

                        TopBar(
                            title = "Hello World!",
                            scrollBehavior = scrollBehavior
                        )

                    },
                    content = {

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            contentAlignment = Alignment.Center,
                            content = { Text(text = "TopBar test") }
                        )

                    }
                )

            }
        }
    }

    @Test
    fun assertTopBarIsDisplayed() = runTest {
        composeRule.awaitIdle()
        composeRule.onNodeWithText("Hello World!").assertIsDisplayed()
    }

    @Test
    fun assertTopBarIsCollapsed() = runTest {
        composeRule.awaitIdle()
        composeRule.onNodeWithText("Hello World!").performTouchInput { swipeUp() }
        composeRule.awaitIdle()
        composeRule.onNodeWithTag("top_bar")
            .assertHeightIsAtLeast(TopBarDefaults.smallContainerHeight)
    }

}