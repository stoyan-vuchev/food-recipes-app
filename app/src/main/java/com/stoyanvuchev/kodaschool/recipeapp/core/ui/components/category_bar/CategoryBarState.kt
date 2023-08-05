package com.stoyanvuchev.kodaschool.recipeapp.core.ui.components.category_bar

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable

/**
 * Creates a [CategoryBarState] that is remembered across compositions.
 *
 * Changes to the provided initial values will **not** result in the state being recreated or
 * changed in any way if it has already been created.
 *
 * @param initialFirstVisibleItemIndex the initial value for the first visible item index.
 * @param initialFirstVisibleItemScrollOffset the initial value for the first visible item scroll offset.
 */
@Composable
fun rememberCategoryBarState(
    initialFirstVisibleItemIndex: Int = 0,
    initialFirstVisibleItemScrollOffset: Int = 0
): CategoryBarState {
    return rememberSaveable(saver = CategoryBarState.Saver) {
        CategoryBarState(
            initialFirstVisibleItemIndex,
            initialFirstVisibleItemScrollOffset
        )
    }
}

/** Using [CategoryBarState] as a typealias of [LazyListState] to avoid confusion. */
typealias CategoryBarState = LazyListState