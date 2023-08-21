package com.stoyanvuchev.kodaschool.recipeapp.presentation.splash.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.NavigationBarScrim
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.StatusBarScrim
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.button.Button
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import com.stoyanvuchev.kodaschool.recipeapp.presentation.splash.SplashScreenUiAction

@Composable
fun SplashScreen(
    isSetupCompleted: Boolean?,
    onUiAction: (SplashScreenUiAction) -> Unit
) {

    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = isSetupCompleted != null && !isSetupCompleted,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Theme.colors.background,
            contentColor = Theme.colors.onBackground,
            topBar = { StatusBarScrim() },
            bottomBar = { NavigationBarScrim() }
        ) { paddingValues ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .widthIn(max = 480.dp)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 40.dp, vertical = 24.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo"
                        )

                    }

                    Spacer(modifier = Modifier.height(48.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.splash_art),
                            contentDescription = "Splash art"
                        )

                    }

                    Spacer(modifier = Modifier.height(48.dp))

                    Text(
                        text = stringResource(id = R.string.splash_screen_title),
                        style = Theme.typography.largeTitle,
                        color = Theme.colors.onBackground
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        text = stringResource(id = R.string.splash_screen_description),
                        style = Theme.typography.regularText,
                        color = Theme.colors.onBackground.copy(alpha = .8f)
                    )

                    Spacer(modifier = Modifier.height(48.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {

                        Button(
                            onClick = remember { { onUiAction(SplashScreenUiAction.CompleteSetup) } },
                            shape = Theme.shapes.medium,
                            contentPadding = PaddingValues(
                                horizontal = ComponentsTokens.Button.horizontalPadding,
                                vertical = ComponentsTokens.Button.verticalPadding * 2
                            )
                        ) {

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = stringResource(id = R.string.splash_screen_button_text),
                                style = Theme.typography.sectionTitle.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = Icons.Rounded.ArrowForward,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                        }

                    }

                }

            }

        }

    }

}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SplashScreenPreview() {
    FoodRecipesTheme {
        SplashScreen(
            isSetupCompleted = false,
            onUiAction = {}
        )
    }
}