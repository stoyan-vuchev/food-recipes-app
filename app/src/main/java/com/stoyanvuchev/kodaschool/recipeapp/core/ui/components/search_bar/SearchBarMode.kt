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

/** Modes for defining a [SearchBar] behavior. */
enum class SearchBarMode {

    /**
     * This mode is used for scenario when the [SearchBar] is placed in a screen
     * that is not intended to handle searching, therefore,
     * it should handle a click event instead, and take the user to a screen
     * with a [SearchBar] configured in [SearchBarMode.Search].
     **/
    Navigate,

    /**
     * This mode is used when a [SearchBar] is placed in a screen
     * capable of handling search capabilities.
     **/
    Search

}