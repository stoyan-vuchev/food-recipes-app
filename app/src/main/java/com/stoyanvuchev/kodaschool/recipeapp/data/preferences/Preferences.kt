package com.stoyanvuchev.kodaschool.recipeapp.data.preferences

interface Preferences {
    suspend fun getIsSetupCompleted(): Boolean?
    suspend fun setIsSetupCompleted(isSetupCompleted: Boolean)
}