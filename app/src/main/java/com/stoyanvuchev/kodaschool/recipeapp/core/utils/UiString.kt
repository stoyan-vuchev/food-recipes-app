package com.stoyanvuchev.kodaschool.recipeapp.core.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.stringResource

/** Utility for a string support outside the UI scope. */
@Immutable
sealed interface UiString {

    /** Creates [UiString] from a normal [String]. */
    data class FromString(val value: String) : UiString

    /** Creates [UiString] from a String resource reference. */
    class FromResource(@StringRes val resId: Int, vararg val args: Any) : UiString

    /** Extracts the [String] from the [UiString] inside a Composable. */
    @Composable
    fun asString(): String = when (this) {
        is FromString -> value
        is FromResource -> stringResource(resId, *args)
    }

    /**
     * Extracts the string resource reference from the [UiString].
     * @param context required for accessing the ContentResolver and extract the [String].
     * */
    fun asString(context: Context): String = when (this) {
        is FromString -> value
        is FromResource -> context.resources.getString(resId, *args)
    }

    companion object {

        /** Declares [UiString] with an empty [String]. */
        val EmptyString = FromString("")

    }

}