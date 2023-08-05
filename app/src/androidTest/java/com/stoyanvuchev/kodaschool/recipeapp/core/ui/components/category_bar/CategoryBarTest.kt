package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val categories = listOf(
        RecipesCategory.Breakfast,
        RecipesCategory.Lunch,
        RecipesCategory.Dinner,
        RecipesCategory.Teatime,
        RecipesCategory.Snack
    )

    private var selectedCategory = categories.first()
    private lateinit var categoryBarState: CategoryBarState

    @Before
    fun setUp() {
        composeTestRule.setContent {
            FoodRecipesTheme {
                categoryBarState = rememberCategoryBarState()
                Box(
                    modifier = Modifier.fillMaxSize(),
                    content = {

                        CategoryBar(
                            state = categoryBarState,
                            items = {

                                items(
                                    items = categories,
                                    key = { "category_$it" },
                                    itemContent = { category ->

                                        CategoryBarItemContent(
                                            selected = selectedCategory == category,
                                            onSelected = remember {
                                                { selectedCategory = category }
                                            },
                                            label = category.name
                                        )

                                    }
                                )

                            }
                        )

                    }
                )
            }
        }
    }

    @Test
    fun assertTheCategoryBarIsDisplayed() = runTest {
        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithTag("category_bar").assertIsDisplayed()
    }

    @Test
    fun assertTheCategoryBarIsWorking() = runTest {
        composeTestRule.awaitIdle()
        val expectedCategory = categories[1]
        composeTestRule.onNodeWithText(expectedCategory.name).performClick()
        assertThat(selectedCategory).isEqualTo(expectedCategory)
    }

}