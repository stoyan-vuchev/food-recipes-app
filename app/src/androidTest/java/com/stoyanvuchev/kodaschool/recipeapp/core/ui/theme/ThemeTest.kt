package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.color.darkColors
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.color.lightColors
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.shape.Shapes
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.typography.Typography
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ThemeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun verifyLightThemeColorsValuesAreTheSameAsTheSpecifiedValues() = runTest {
        val colors = lightColors()
        composeTestRule.setContent {
            FoodRecipesTheme(darkTheme = false) {
                assertThat(Theme.colors.primary).isEqualTo(colors.primary)
                assertThat(Theme.colors.onPrimary).isEqualTo(colors.onPrimary)
                assertThat(Theme.colors.primaryContainer).isEqualTo(colors.primaryContainer)
                assertThat(Theme.colors.onPrimaryContainer).isEqualTo(colors.onPrimaryContainer)
                assertThat(Theme.colors.accent).isEqualTo(colors.accent)
                assertThat(Theme.colors.onAccent).isEqualTo(colors.onAccent)
                assertThat(Theme.colors.accentContainer).isEqualTo(colors.accentContainer)
                assertThat(Theme.colors.onAccentContainer).isEqualTo(colors.onAccentContainer)
                assertThat(Theme.colors.background).isEqualTo(colors.background)
                assertThat(Theme.colors.onBackground).isEqualTo(colors.onBackground)
                assertThat(Theme.colors.backgroundContainer).isEqualTo(colors.backgroundContainer)
                assertThat(Theme.colors.onBackgroundContainer).isEqualTo(colors.onBackgroundContainer)
                assertThat(Theme.colors.error).isEqualTo(colors.error)
                assertThat(Theme.colors.onError).isEqualTo(colors.onError)
                assertThat(Theme.colors.errorContainer).isEqualTo(colors.errorContainer)
                assertThat(Theme.colors.onErrorContainer).isEqualTo(colors.onErrorContainer)
                assertThat(Theme.colors.outline).isEqualTo(colors.outline)
                assertThat(Theme.colors.outlineVariant).isEqualTo(colors.outlineVariant)
            }
        }
    }

    @Test
    fun verifyDarkThemeColorsValuesAreTheSameAsTheSpecifiedValues() = runTest {
        val colors = darkColors()
        composeTestRule.setContent {
            FoodRecipesTheme(darkTheme = true) {
                assertThat(Theme.colors.primary).isEqualTo(colors.primary)
                assertThat(Theme.colors.onPrimary).isEqualTo(colors.onPrimary)
                assertThat(Theme.colors.primaryContainer).isEqualTo(colors.primaryContainer)
                assertThat(Theme.colors.onPrimaryContainer).isEqualTo(colors.onPrimaryContainer)
                assertThat(Theme.colors.accent).isEqualTo(colors.accent)
                assertThat(Theme.colors.onAccent).isEqualTo(colors.onAccent)
                assertThat(Theme.colors.accentContainer).isEqualTo(colors.accentContainer)
                assertThat(Theme.colors.onAccentContainer).isEqualTo(colors.onAccentContainer)
                assertThat(Theme.colors.background).isEqualTo(colors.background)
                assertThat(Theme.colors.onBackground).isEqualTo(colors.onBackground)
                assertThat(Theme.colors.backgroundContainer).isEqualTo(colors.backgroundContainer)
                assertThat(Theme.colors.onBackgroundContainer).isEqualTo(colors.onBackgroundContainer)
                assertThat(Theme.colors.error).isEqualTo(colors.error)
                assertThat(Theme.colors.onError).isEqualTo(colors.onError)
                assertThat(Theme.colors.errorContainer).isEqualTo(colors.errorContainer)
                assertThat(Theme.colors.onErrorContainer).isEqualTo(colors.onErrorContainer)
                assertThat(Theme.colors.outline).isEqualTo(colors.outline)
                assertThat(Theme.colors.outlineVariant).isEqualTo(colors.outlineVariant)
            }
        }
    }

    @Test
    fun verifyThemeTypographyValuesAreTheSameAsTheSpecifiedValues() = runTest {
        val typography = Typography()
        composeTestRule.setContent {
            FoodRecipesTheme {
                assertThat(Theme.typography.regularText).isEqualTo(typography.regularText)
                assertThat(Theme.typography.sectionTitle).isEqualTo(typography.sectionTitle)
                assertThat(Theme.typography.largeTitle).isEqualTo(typography.largeTitle)
            }
        }
    }

    @Test
    fun verifyThemeShapeValuesAreTheSameAsTheSpecifiedValues() = runTest {
        val shapes = Shapes()
        composeTestRule.setContent {
            FoodRecipesTheme {
                assertThat(Theme.shapes.small).isEqualTo(shapes.small)
                assertThat(Theme.shapes.medium).isEqualTo(shapes.medium)
                assertThat(Theme.shapes.large).isEqualTo(shapes.large)
                assertThat(Theme.shapes.extraLarge).isEqualTo(shapes.extraLarge)
            }
        }
    }

}