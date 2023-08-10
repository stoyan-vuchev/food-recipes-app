package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.topbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

/**
 *
 * Creates a [TopBarState] that is remembered across compositions.
 *
 * @param initialHeightOffsetLimit the initial value for [TopBarState.heightOffsetLimit],
 * which represents the pixel limit that a top bar is allowed to collapse when the scrollable
 * content is scrolled.
 *
 * @param initialHeightOffset the initial value for [TopBarState.heightOffset]. The initial
 * offset height offset should be between zero and [initialHeightOffsetLimit].
 *
 * @param initialContentOffset the initial value for [TopBarState.contentOffset].
 *
 */
@Composable
fun rememberTopBarState(
    initialHeightOffsetLimit: Float = -Float.MAX_VALUE,
    initialHeightOffset: Float = 0f,
    initialContentOffset: Float = 0f
): TopBarState = rememberSaveable(saver = TopBarState.Saver) {
    TopBarState(
        initialHeightOffsetLimit,
        initialHeightOffset,
        initialContentOffset
    )
}

@Stable
class TopBarState(
    initialHeightOffsetLimit: Float,
    initialHeightOffset: Float,
    initialContentOffset: Float
) {

    private var _heightOffset by mutableStateOf(initialHeightOffset)

    /**
     * The total offset of the content scrolled under the top bar.
     *
     * The content offset is used to compute the [overlappedFraction], which can later be read
     * by an implementation.
     *
     * This value is updated by a [TopBarScrollBehavior] whenever a nested scroll connection
     * consumes scroll events. A common implementation would update the value to be the sum of all
     * [NestedScrollConnection.onPostScroll] `consumed.y` values.
     */
    var contentOffset by mutableStateOf(initialContentOffset)

    /**
     * The top bar's height offset limit in pixels, which represents the limit that a top
     * bar is allowed to collapse to.
     *
     * Use this limit to coerce the [heightOffset] value when it's updated.
     */
    var heightOffsetLimit by mutableStateOf(initialHeightOffsetLimit)

    /**
     * The top bar's current height offset in pixels. This height offset is applied to the fixed
     * height of the top bar to control the displayed height when content is being scrolled.
     *
     * Updates to the [heightOffset] value are coerced between zero and [heightOffsetLimit].
     */
    var heightOffset: Float
        get() = _heightOffset
        set(newOffset) {
            _heightOffset = newOffset.coerceIn(
                minimumValue = heightOffsetLimit,
                maximumValue = 0f
            )
        }

    /**
     * A value that represents the collapsed height percentage of the top bar.
     *
     * A `0.0` represents a fully expanded bar, and `1.0` represents a fully collapsed bar (computed
     * as [heightOffset] / [heightOffsetLimit]).
     */
    val collapsedFraction: Float
        get() = if (heightOffsetLimit != 0f) heightOffset / heightOffsetLimit else 0f

    /**
     * A value that represents the percentage of the top bar area that is overlapping with the
     * content scrolled behind it.
     *
     * A `0.0` indicates that the top bar does not overlap any content, while `1.0` indicates that
     * the entire visible top bar area overlaps the scrolled content.
     */
    val overlappedFraction: Float
        get() = if (heightOffsetLimit != 0f) {
            1 - ((heightOffsetLimit - contentOffset).coerceIn(
                minimumValue = heightOffsetLimit,
                maximumValue = 0f
            ) / heightOffsetLimit)
        } else {
            0f
        }


    @Composable
    fun bottomOffset(): Float {
        val offset by rememberUpdatedState(
            (heightOffsetLimit +
                    heightOffset.absoluteValue).absoluteValue.roundToInt().toFloat()
        )
        return offset
    }

    companion object {

        /**
         * The default [Saver] implementation for [TopBarState].
         */
        val Saver: Saver<TopBarState, *> = listSaver(
            save = { listOf(it.heightOffsetLimit, it.heightOffset, it.contentOffset) },
            restore = {
                TopBarState(
                    initialHeightOffsetLimit = it[0],
                    initialHeightOffset = it[1],
                    initialContentOffset = it[2]
                )
            }
        )

    }

}