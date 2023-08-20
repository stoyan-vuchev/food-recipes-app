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

package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.recipe_grid_item

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.shape.ShapeTokens

@Composable
fun RecipeGridItemLoadingShimmer(
    modifier: Modifier = Modifier,
    isSaveButtonVisible: Boolean = true,
) {

    var intSize by remember {
        mutableStateOf(
            IntSize(
                width = 128,
                height = 188 + if (isSaveButtonVisible) 28 else 0
            )
        )
    }

    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -4 * intSize.width.toFloat(),
        targetValue = 3 * intSize.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000
            )
        ),
        label = ""
    )

    val startAndEndColor = Theme.colors.outline.copy(alpha = .1f)
    val middleColor = Theme.colors.outline.copy(alpha = .2f)

    val shimmerBrush = Brush.linearGradient(
        colors = listOf(
            startAndEndColor,
            middleColor,
            startAndEndColor
        ),
        start = Offset(
            x = startOffsetX,
            y = 0f
        ),
        end = Offset(
            x = startOffsetX + intSize.width.toFloat(),
            y = intSize.height.toFloat()
        )
    )

    Column(
        modifier = Modifier
            .defaultMinSize(minWidth = 128.dp)
            .onGloballyPositioned { intSize = it.size }
            .then(modifier)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(
                    brush = shimmerBrush,
                    shape = ShapeTokens.MediumShape
                )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .background(
                    brush = shimmerBrush,
                    shape = ShapeTokens.SmallShape
                )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(16.dp)
                .background(
                    brush = shimmerBrush,
                    shape = ShapeTokens.SmallShape
                )
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (isSaveButtonVisible) {

            Box(
                modifier = Modifier
                    .width(72.dp)
                    .height(28.dp)
                    .background(
                        brush = shimmerBrush,
                        shape = ShapeTokens.ExtraLargeShape
                    )
            )

        }

    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RecipeGridItemLoadingShimmerPreview() {
    FoodRecipesTheme {
        Box(
            modifier = Modifier.size(360.dp),
            contentAlignment = Alignment.Center,
            content = { RecipeGridItemLoadingShimmer() }
        )
    }
}