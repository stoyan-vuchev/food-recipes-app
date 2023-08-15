package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.search_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isTrue
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    private var mode = SearchBarMode.Navigate
    private var isClicked = false
    private val testTag = "search_bar"
    private val hint = "Search for recipes"

    @Before
    fun setUp() {
        composeRule.setContent {
            FoodRecipesTheme {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    var queryText by remember { mutableStateOf("") }

                    SearchBar(
                        modifier = Modifier.testTag(testTag),
                        queryText = queryText,
                        onQueryText = remember { { queryText = it } },
                        queryHint = hint,
                        onClickOrImeAction = remember { { isClicked = !isClicked } },
                        mode = mode
                    )
                }

            }
        }
    }

    @Test
    fun assertTheSearchBarIsDisplayed() = runTest {
        composeRule.awaitIdle()
        composeRule.onNodeWithTag(testTag).assertIsDisplayed()
    }

    @Test
    fun assertTheSearchBarIsNavigateModeAndItIsClickable() = runTest {

        isClicked = false
        mode = SearchBarMode.Navigate

        composeRule.awaitIdle()
        composeRule.onNodeWithTag(testTag).performClick()

        composeRule.awaitIdle()
        assertThat(isClicked).isTrue()

    }

}