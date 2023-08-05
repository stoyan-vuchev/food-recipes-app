package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.shape

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShapesTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun verifyShapeValuesMatchTokenValues() = runTest {

        val topLevelShapes = Shapes()

        composeTestRule.setContent {

            CompositionLocalProvider(
                LocalShapes provides topLevelShapes,
                content = {

                    val shapes = LocalShapes.current
                    val bgColor = Color.Black
                    val textColor = Color.White

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(textColor)
                            .padding(32.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        content = {

                            Text(
                                modifier = Modifier
                                    .height(48.dp)
                                    .clip(shapes.small)
                                    .background(bgColor)
                                    .padding(horizontal = 24.dp),
                                text = "Small",
                                color = textColor,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                modifier = Modifier
                                    .height(48.dp)
                                    .clip(shapes.medium)
                                    .background(bgColor)
                                    .padding(horizontal = 24.dp),
                                text = "Medium",
                                color = textColor,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                modifier = Modifier
                                    .height(48.dp)
                                    .clip(shapes.large)
                                    .background(bgColor)
                                    .padding(horizontal = 24.dp),
                                text = "Large",
                                color = textColor,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                modifier = Modifier
                                    .height(48.dp)
                                    .clip(shapes.extraLarge)
                                    .background(bgColor)
                                    .padding(horizontal = 24.dp),
                                text = "Extra",
                                color = textColor,
                                textAlign = TextAlign.Center
                            )

                        }
                    )

                }
            )

        }

        composeTestRule.awaitIdle()
        validateShapes(topLevelShapes)

    }

    private fun validateShapes(shapes: Shapes) = runTest {
        assertThat(shapes.small).isEqualTo(ShapeTokens.SmallShape)
        assertThat(shapes.medium).isEqualTo(ShapeTokens.MediumShape)
        assertThat(shapes.large).isEqualTo(ShapeTokens.LargeShape)
        assertThat(shapes.extraLarge).isEqualTo(ShapeTokens.ExtraLargeShape)
    }

}