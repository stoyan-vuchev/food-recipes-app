package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.typography

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TypographyTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun typographyMatchesTheSpecifiedValues() = runTest {

        val topLevelTypography = Typography()

        composeTestRule.setContent {

            CompositionLocalProvider(
                LocalTypography provides topLevelTypography,
                content = {

                    val typography = LocalTypography.current
                    val text = "The quick brown fox jumps over the lazy dog."
                    val textColor = Color.White

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Black)
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        content = {

                            Text(
                                text = text,
                                color = textColor,
                                style = typography.largeTitle
                            )

                            Text(
                                text = text,
                                color = textColor,
                                style = typography.sectionTitle
                            )

                            Text(
                                text = text,
                                color = textColor,
                                style = typography.regularText
                            )

                        }
                    )

                }
            )

        }

        composeTestRule.awaitIdle()
        validateTypography(topLevelTypography)

    }

    private fun validateTypography(typography: Typography) = runTest {
        assertThat(typography.regularText).isEqualTo(TypographyTokens.RegularText)
        assertThat(typography.sectionTitle).isEqualTo(TypographyTokens.SectionTitle)
        assertThat(typography.largeTitle).isEqualTo(TypographyTokens.LargeTitle)
    }

}