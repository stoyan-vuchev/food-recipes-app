package com.stoyanvuchev.kodaschool.recipeapp.core.utils

import com.stoyanvuchev.kodaschool.recipeapp.R

/** Utility for retrieving resources with a safe manner. */
sealed interface Result<T> {

    /** A [Result] indicating the resource is being loaded. */
    class Loading<T> : Result<T>

    /** Indicating a successful [Result] with resource. */
    data class Success<T>(val data: T) : Result<T>

    /** Indicating unsuccessful [Result] caused by an [Exception] or something related. */
    data class Error<T>(val error: UiString) : Result<T>

    companion object {

        /**
         * Creates [UiString] from an error [String] message. If the error message is null, it returns
         * ```
         * UiString.FromResource(resId = R.string.something_went_wrong
         * ```
         * Otherwise
         * ```
         * UiString.FromString(error)
         * ```
         * @param error the error message.
         **/
        fun uiStringError(error: String? = null): UiString {
            return error?.let { UiString.FromString(error) }
                ?: UiString.FromResource(resId = R.string.something_went_wrong)
        }

    }

}