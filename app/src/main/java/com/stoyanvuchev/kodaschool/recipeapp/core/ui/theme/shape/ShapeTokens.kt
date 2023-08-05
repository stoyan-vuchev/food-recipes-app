package com.stoyanvuchev.kodaschool.recipeapp.core.ui.theme.shape

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import sv.lib.squircleshape.SquircleShape

/** Default shape tokens used by the design system. */
@Immutable
object ShapeTokens {

    val SmallShapeRadius: Dp get() = 8.dp
    val MediumShapeRadius: Dp get() = 12.dp
    val LargeShapeRadius: Dp get() = 20.dp
    val ExtraLargeShapeRadius: Dp get() = 32.dp

    val SmallShape: Shape get() = SquircleShape(radius = SmallShapeRadius)
    val MediumShape: Shape get() = SquircleShape(radius = MediumShapeRadius)
    val LargeShape: Shape get() = SquircleShape(radius = LargeShapeRadius)
    val ExtraLargeShape: Shape get() = SquircleShape(radius = ExtraLargeShapeRadius)

}