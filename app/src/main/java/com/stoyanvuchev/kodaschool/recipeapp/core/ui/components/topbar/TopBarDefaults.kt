/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.topbar

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.ComponentsTokens

/** The default property values of a [TopBar]. */
object TopBarDefaults {

    /** The default height of the small [TopBar] or large [TopBar] in collapsed state. */
    val smallContainerHeight: Dp get() = ComponentsTokens.TopBar.smallContainerHeight

    /** The default height of the large [TopBar] in expanded state. */
    val largeContainerHeight: Dp get() = ComponentsTokens.TopBar.largeContainerHeight

    /** The background content color of a [TopBar]. */
    val backgroundColor: Color @Composable get() = ComponentsTokens.TopBar.backgroundColor

    /** The default content color of a [TopBar]. */
    val contentColor: Color @Composable get() = ComponentsTokens.TopBar.contentColor

    /** The default [WindowInsets] values of a [TopBar]. */
    val windowInsets: WindowInsets @Composable get() = ComponentsTokens.TopBar.windowInsets

    /**
     *
     * Returns a [TopBarScrollBehavior] that adjusts its properties to affect the colors and
     * height of the top bar.
     *
     * A top bar that is set up with this [TopBarScrollBehavior] will immediately collapse
     * when the nested content is pulled up, and will expand back the collapsed area when the
     * content is  pulled all the way down.
     *
     * @param state the state object to be used to control or observe the top bar's scroll
     * state. See [rememberTopBarState] for a state that is remembered across compositions.
     *
     * @param canScroll a callback used to determine whether scroll events are to be
     * handled by this [ExitUntilCollapsedScrollBehavior].
     *
     * @param snapAnimationSpec an optional [AnimationSpec] that defines how the top bar snaps
     * to either fully collapsed or fully extended state when a fling or a drag scrolled it into an
     * intermediate position.
     *
     * @param flingAnimationSpec an optional [DecayAnimationSpec] that defined how to fling the top
     * bar when the user flings the top bar itself, or the content below it.
     *
     */
    @Composable
    fun exitUntilCollapsedScrollBehavior(
        state: TopBarState = rememberTopBarState(),
        canScroll: () -> Boolean = { true },
        snapAnimationSpec: AnimationSpec<Float>? = spring(),
        flingAnimationSpec: DecayAnimationSpec<Float>? = rememberSplineBasedDecay()
    ): TopBarScrollBehavior = ExitUntilCollapsedScrollBehavior(
        state = state,
        snapAnimationSpec = snapAnimationSpec,
        flingAnimationSpec = flingAnimationSpec,
        canScroll = canScroll
    )

}