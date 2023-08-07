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

package com.stoyanvuchev.kodaschool.recipeapp.presentation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.UiString

/**
 * An abstraction used to define a screen destination.
 * @param route The route of the screen destination.
 * @param label A label to be displayed in a navigation bar item. (optional)
 * @param icon An icon to be displayed in a navigation bar item. (optional)
 **/
@Immutable
abstract class ScreenDestination(
    val route: String,
    val label: UiString? = null,
    @DrawableRes val icon: Int? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ScreenDestination) return false
        if (route != other.route) return false
        if (label != other.label) return false
        if (icon != other.icon) return false
        return true
    }

    override fun hashCode(): Int {
        var result = route.hashCode()
        result = 31 * result + label.hashCode()
        result = 31 * result + icon.hashCode()
        return result
    }

    override fun toString(): String {
        return "ScreenDestination(route=$route, label=$label, selectedIcon=$icon)"
    }

}