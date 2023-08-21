package com.stoyanvuchev.kodaschool.recipeapp.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesImpl @Inject constructor(
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
) : Preferences {

    override suspend fun getIsSetupCompleted(): Boolean? {
        return dataStore.data.map { it[IS_SETUP_COMPLETE] }.first()
    }

    override suspend fun setIsSetupCompleted(isSetupCompleted: Boolean) {
        dataStore.edit { it[IS_SETUP_COMPLETE] = isSetupCompleted }
    }

    companion object {
        val Context.dataStore by preferencesDataStore(name = "food_recipes_app_preferences")
        private val IS_SETUP_COMPLETE = booleanPreferencesKey("is_setup_complete")
    }

}