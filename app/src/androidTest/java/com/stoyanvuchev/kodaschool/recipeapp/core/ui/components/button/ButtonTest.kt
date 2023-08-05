package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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
class ButtonTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val buttonText = "Click me!"
    private var isClicked = false

    @Before
    fun setUp() {
        composeRule.setContent {
            FoodRecipesTheme {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                    content = {

                        Button(
                            onClick = remember { { isClicked = true } },
                            content = { Text(text = buttonText) }
                        )

                    }
                )

            }
        }
    }

    @Test
    fun assertTheButtonIsDisplayed() = runTest {
        composeRule.awaitIdle()
        composeRule.onNodeWithText(buttonText).assertIsDisplayed()
    }

    @Test
    fun assertTheButtonIsClicked() = runTest {
        composeRule.awaitIdle()
        composeRule.onNodeWithText(buttonText).performClick()
        composeRule.awaitIdle()
        assertThat(isClicked).isTrue()
    }

}