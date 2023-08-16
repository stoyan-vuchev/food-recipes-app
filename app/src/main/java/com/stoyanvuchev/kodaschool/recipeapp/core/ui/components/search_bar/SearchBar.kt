/*
 *
 *   MIT License
 *
 *   Copyright (c) 2023 Stoyan Vuchev
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 *
 */

package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.search_bar

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.kodaschool.recipeapp.R
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme

/**
 * A custom search bar component for navigating and performing search queries.
 * @param modifier allows further customization.
 * @param queryText the text of the search bar text field.
 * @param onQueryText the callback invoked whenever a new text is received from the input.
 * @param queryHint the hint of the search bar text field.
 * @param mode the mode that defines the search bar behavior.
 * @param onClickOrImeAction the callback invoked whenever the search bar is clicked
 * (in [SearchBarMode.Navigate]) or by the keyboard ime action.
 * @param enabled whether the search bar is enabled or not.
 * @param colors the colors of the search bar.
 * @param textStyle the text style of the search bar hint and text field text.
 * @param shape the shape of the search bar.
 * @param maxLength the maximum allowed [onQueryText] length.
 **/
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    queryText: String,
    onQueryText: (String) -> Unit,
    queryHint: String,
    mode: SearchBarMode = SearchBarMode.Search,
    onClickOrImeAction: () -> Unit,
    enabled: Boolean = true,
    colors: SearchBarColors = SearchBarDefaults.colors(),
    textStyle: TextStyle = SearchBarDefaults.textStyle,
    shape: Shape = SearchBarDefaults.shape,
    maxLength: Int = 64
) {

    CompositionLocalProvider(
        LocalContentColor provides colors.contentColor,
        LocalTextStyle provides textStyle
    ) {

        Box(
            modifier = Modifier
                .padding(horizontal = ComponentsTokens.SearchBar.horizontalContainerPadding)
                .fillMaxWidth()
                .height(ComponentsTokens.SearchBar.containerHeight)
                .border(
                    width = SearchBarDefaults.borderWidth,
                    color = colors.borderColor,
                    shape = shape
                )
                .clickable(
                    enabled = enabled,
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = if (mode == SearchBarMode.Navigate) onClickOrImeAction else ({})
                )
                .padding(horizontal = ComponentsTokens.SearchBar.horizontalContentPadding)
                .then(modifier),
            contentAlignment = Alignment.CenterStart
        ) {

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        end = ComponentsTokens.SearchBar.horizontalContentPadding +
                                ComponentsTokens.SearchBar.iconSize
                    ),
                value = queryText,
                onValueChange = remember { { if (it.length <= maxLength) onQueryText(it) } },
                readOnly = mode == SearchBarMode.Navigate,
                singleLine = true,
                textStyle = textStyle.copy(color = colors.contentColor),
                enabled = enabled && mode == SearchBarMode.Search,
                cursorBrush = SolidColor(Theme.colors.primary),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onAny = remember { { onClickOrImeAction() } })
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    ComponentsTokens.SearchBar.horizontalContentPadding
                )
            ) {

                Text(
                    modifier = Modifier.weight(1f),
                    text = if (queryText.isEmpty()) queryHint else "",
                    color = colors.borderColor
                )

                Icon(
                    modifier = Modifier.size(ComponentsTokens.SearchBar.iconSize),
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = null
                )

            }

        }

    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
private fun SearchBarPreviewLight() {
    FoodRecipesTheme {
        Box(
            modifier = Modifier
                .width(480.dp)
                .height(88.dp),
            contentAlignment = Alignment.Center
        ) {
            SearchBar(
                queryText = "",
                onQueryText = {},
                queryHint = "Search for recipes",
                onClickOrImeAction = {}
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SearchBarPreviewDark() {
    FoodRecipesTheme {
        Box(
            modifier = Modifier
                .width(480.dp)
                .height(88.dp),
            contentAlignment = Alignment.Center
        ) {
            SearchBar(
                queryText = "",
                onQueryText = {},
                queryHint = "Search for recipes",
                onClickOrImeAction = {}
            )
        }
    }
}