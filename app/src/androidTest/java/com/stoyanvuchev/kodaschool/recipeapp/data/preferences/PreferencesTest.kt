package com.stoyanvuchev.kodaschool.recipeapp.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PreferencesTest {

    companion object {
        const val TEST_DATASTORE_PREFERENCES_NAME = "food_recipes_app_preferences_test"
        private val IS_SETUP_COMPLETE = booleanPreferencesKey("is_setup_complete_test")
    }

    private lateinit var dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
    private lateinit var testCoroutineScope: TestScope

    @Before
    fun setUp() {
        val testContext = ApplicationProvider.getApplicationContext<Context>()
        testCoroutineScope = TestScope(TestCoroutineScheduler() + Job())
        dataStore = PreferenceDataStoreFactory.create(
            scope = testCoroutineScope,
            produceFile = {
                testContext.preferencesDataStoreFile(name = TEST_DATASTORE_PREFERENCES_NAME)
            }
        )
    }

    @Test
    fun assertInitialIsSetupCompleteValueIsFalse() {
        testCoroutineScope.launch {
            dataStore.edit { it[IS_SETUP_COMPLETE] = false }
            dataStore.data.test {
                val actual = awaitItem()[IS_SETUP_COMPLETE]
                awaitComplete()
                assertThat(actual).isNotNull().isFalse()
            }
        }
    }

    @Test
    fun assertInitialIsSetupCompleteValueIsTrue() {
        testCoroutineScope.launch {
            dataStore.edit { it[IS_SETUP_COMPLETE] = true }
            dataStore.data.test {
                val actual = awaitItem()[IS_SETUP_COMPLETE]
                awaitComplete()
                assertThat(actual).isNotNull().isTrue()
            }
        }
    }

}