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
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.FoodRecipesTheme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.Theme
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.shape.ShapeTokens
import sv.lib.squircleshape.drawSquircle

@Composable
fun RecipeGridItemLoadingShimmer(
    modifier: Modifier = Modifier
) {

    var intSize by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * intSize.width.toFloat(),
        targetValue = 2 * intSize.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        ),
        label = ""
    )

    val startAndEndColor = Theme.colors.outlineVariant
    val middleColor = Theme.colors.outline

    Canvas(
        modifier = Modifier
            .width(128.dp)
            .height(220.dp)
            .onGloballyPositioned { intSize = it.size }
            .then(modifier),
        onDraw = {

            val shimmerBrush = Brush.linearGradient(
                colors = listOf(
                    startAndEndColor,
                    middleColor.copy(alpha = .2f),
                    startAndEndColor
                ),
                start = Offset(
                    x = startOffsetX,
                    y = 0f
                ),
                end = Offset(
                    x = startOffsetX + this.size.width,
                    y = this.size.height
                )
            )

            val imgPlaceholderCornerRadius = ShapeTokens.MediumShapeRadius.toPx()
            val labelPlaceholderCornerRadius = ShapeTokens.SmallShapeRadius.toPx()
            val buttonPlaceholderCornerRadius = ShapeTokens.ExtraLargeShapeRadius.toPx()

            drawSquircle(
                brush = shimmerBrush,
                topLeft = Offset.Zero,
                size = Size(
                    width = this.size.width,
                    height = this.size.width
                ),
                topLeftCorner = imgPlaceholderCornerRadius,
                topRightCorner = imgPlaceholderCornerRadius,
                bottomRightCorner = imgPlaceholderCornerRadius,
                bottomLeftCorner = imgPlaceholderCornerRadius,
            )

            drawSquircle(
                brush = shimmerBrush,
                topLeft = Offset(
                    x = 0f,
                    y = 140.dp.toPx()
                ),
                size = Size(
                    width = this.size.width,
                    height = 16.dp.toPx()
                ),
                topLeftCorner = labelPlaceholderCornerRadius,
                topRightCorner = labelPlaceholderCornerRadius,
                bottomRightCorner = labelPlaceholderCornerRadius,
                bottomLeftCorner = labelPlaceholderCornerRadius,
            )

            drawSquircle(
                brush = shimmerBrush,
                topLeft = Offset(
                    x = 0f,
                    y = 162.dp.toPx()
                ),
                size = Size(
                    width = this.size.width * 0.67f,
                    height = 16.dp.toPx()
                ),
                topLeftCorner = labelPlaceholderCornerRadius,
                topRightCorner = labelPlaceholderCornerRadius,
                bottomRightCorner = labelPlaceholderCornerRadius,
                bottomLeftCorner = labelPlaceholderCornerRadius,
            )

            drawSquircle(
                brush = shimmerBrush,
                topLeft = Offset(
                    x = 0f,
                    y = 192.dp.toPx()
                ),
                size = Size(
                    width = this.center.x,
                    height = 28.dp.toPx()
                ),
                topLeftCorner = buttonPlaceholderCornerRadius,
                topRightCorner = buttonPlaceholderCornerRadius,
                bottomRightCorner = buttonPlaceholderCornerRadius,
                bottomLeftCorner = buttonPlaceholderCornerRadius,
            )

        }
    )

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