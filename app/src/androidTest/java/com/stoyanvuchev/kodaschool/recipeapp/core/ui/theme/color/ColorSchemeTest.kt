package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.color

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ColorSchemeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun lightColorSchemeMatchesTheSpecifiedValues() = runTest {

        val topLevelColors = lightColors()

        composeTestRule.setContent {

            CompositionLocalProvider(
                LocalColors provides topLevelColors,
                content = {

                    val colors = LocalColors.current

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = colors.background),
                        contentAlignment = Alignment.Center,
                        content = {

                            Text(
                                text = "Testing light colors...",
                                color = colors.onBackground
                            )

                        }
                    )

                }
            )

        }

        composeTestRule.awaitIdle()
        validateLightColors(topLevelColors)

    }

    @Test
    fun darkColorSchemeMatchesTheSpecifiedValues() = runTest {

        val topLevelColors = darkColors()

        composeTestRule.setContent {

            CompositionLocalProvider(
                LocalColors provides topLevelColors,
                content = {

                    val colors = LocalColors.current

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = colors.background),
                        contentAlignment = Alignment.Center,
                        content = {

                            Text(
                                text = "Testing dark colors...",
                                color = colors.onBackground
                            )

                        }
                    )

                }
            )

        }

        composeTestRule.awaitIdle()
        validateDarkColors(topLevelColors)

    }

    private fun validateLightColors(colors: Colors) = runTest {
        assertThat(colors.primary).isEqualTo(LightColorsTokens.Primary)
        assertThat(colors.onPrimary).isEqualTo(LightColorsTokens.OnPrimary)
        assertThat(colors.primaryContainer).isEqualTo(LightColorsTokens.PrimaryContainer)
        assertThat(colors.onPrimaryContainer).isEqualTo(LightColorsTokens.OnPrimaryContainer)
        assertThat(colors.accent).isEqualTo(LightColorsTokens.Accent)
        assertThat(colors.onAccent).isEqualTo(LightColorsTokens.OnAccent)
        assertThat(colors.accentContainer).isEqualTo(LightColorsTokens.AccentContainer)
        assertThat(colors.onAccentContainer).isEqualTo(LightColorsTokens.OnAccentContainer)
        assertThat(colors.background).isEqualTo(LightColorsTokens.Background)
        assertThat(colors.onBackground).isEqualTo(LightColorsTokens.OnBackground)
        assertThat(colors.backgroundContainer).isEqualTo(LightColorsTokens.BackgroundContainer)
        assertThat(colors.onBackgroundContainer).isEqualTo(LightColorsTokens.OnBackgroundContainer)
        assertThat(colors.error).isEqualTo(LightColorsTokens.Error)
        assertThat(colors.onError).isEqualTo(LightColorsTokens.OnError)
        assertThat(colors.errorContainer).isEqualTo(LightColorsTokens.ErrorContainer)
        assertThat(colors.onErrorContainer).isEqualTo(LightColorsTokens.OnErrorContainer)
        assertThat(colors.outline).isEqualTo(LightColorsTokens.Outline)
        assertThat(colors.outlineVariant).isEqualTo(LightColorsTokens.OutlineVariant)
    }

    private fun validateDarkColors(colors: Colors) = runTest {
        assertThat(colors.primary).isEqualTo(DarkColorsTokens.Primary)
        assertThat(colors.onPrimary).isEqualTo(DarkColorsTokens.OnPrimary)
        assertThat(colors.primaryContainer).isEqualTo(DarkColorsTokens.PrimaryContainer)
        assertThat(colors.onPrimaryContainer).isEqualTo(DarkColorsTokens.OnPrimaryContainer)
        assertThat(colors.accent).isEqualTo(DarkColorsTokens.Accent)
        assertThat(colors.onAccent).isEqualTo(DarkColorsTokens.OnAccent)
        assertThat(colors.accentContainer).isEqualTo(DarkColorsTokens.AccentContainer)
        assertThat(colors.onAccentContainer).isEqualTo(DarkColorsTokens.OnAccentContainer)
        assertThat(colors.background).isEqualTo(DarkColorsTokens.Background)
        assertThat(colors.onBackground).isEqualTo(DarkColorsTokens.OnBackground)
        assertThat(colors.backgroundContainer).isEqualTo(DarkColorsTokens.BackgroundContainer)
        assertThat(colors.onBackgroundContainer).isEqualTo(DarkColorsTokens.OnBackgroundContainer)
        assertThat(colors.error).isEqualTo(DarkColorsTokens.Error)
        assertThat(colors.onError).isEqualTo(DarkColorsTokens.OnError)
        assertThat(colors.errorContainer).isEqualTo(DarkColorsTokens.ErrorContainer)
        assertThat(colors.onErrorContainer).isEqualTo(DarkColorsTokens.OnErrorContainer)
        assertThat(colors.outline).isEqualTo(DarkColorsTokens.Outline)
        assertThat(colors.outlineVariant).isEqualTo(DarkColorsTokens.OutlineVariant)
    }

}