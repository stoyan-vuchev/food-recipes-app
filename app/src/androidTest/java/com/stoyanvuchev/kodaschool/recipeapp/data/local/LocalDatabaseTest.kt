package com.stoyanvuchev.kodaschool.recipeapp.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.Result
import com.stoyanvuchev.kodaschool.recipeapp.core.utils.UiString
import com.stoyanvuchev.kodaschool.recipeapp.data.repository.RecipesFakeRepository
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.repository.RecipesRepository
import com.stoyanvuchev.kodaschool.recipeapp.mappers.toRecipeModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDatabaseTest {

    private lateinit var dao: LocalDatabaseDao
    private lateinit var database: LocalDatabase
    private lateinit var repository: RecipesRepository
    private lateinit var scope: TestScope
    private val fakeRecipes = RecipesFakeRepository.fakeRecipes

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>().applicationContext,
            LocalDatabase::class.java
        ).build()
        dao = database.dao
        repository = RecipesFakeRepository(dao)

        scope = TestScope(StandardTestDispatcher())
        scope.launch {
            dao.insertRecipes(fakeRecipes)
        }

    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getRecipesByCategory() = scope.runTest {
        val category = RecipesCategory.Breakfast
        val expected = fakeRecipes.filter { it.category == category }.map { it.toRecipeModel() }
        val actual = repository.getRecipesByCategory(category)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getRecipeById() = scope.runTest {

        val expected = fakeRecipes.first().toRecipeModel()

        repository.getRecipeById(expected.recipeId).test {
            when (val actual = awaitItem()) {
                is Result.Loading -> Unit
                is Result.Success -> assertThat(actual).isEqualTo(expected)
                is Result.Error -> throwException(actual.error)
            }
        }

    }

    @Test
    fun updateRecipeById() = scope.runTest {

        val expected = fakeRecipes.first().copy(isBookmarked = true).toRecipeModel()
        repository.updateRecipe(expected)

        repository.updateRecipe(expected).test {
            when (val actual = awaitItem()) {
                is Result.Loading -> Unit
                is Result.Success -> this.cancelAndConsumeRemainingEvents()
                is Result.Error -> throwException(actual.error)
            }
        }

        repository.getRecipeById(expected.recipeId).test {
            when (val actual = awaitItem()) {
                is Result.Loading -> Unit
                is Result.Success -> assertThat(actual).isEqualTo(expected)
                is Result.Error -> throwException(actual.error)
            }
        }

    }

    private fun throwException(uiString: UiString): Nothing = throw Exception(
        uiString.asString(ApplicationProvider.getApplicationContext())
    )

}