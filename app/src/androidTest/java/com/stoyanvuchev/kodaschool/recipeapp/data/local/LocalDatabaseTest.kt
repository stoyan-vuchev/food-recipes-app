package com.stoyanvuchev.kodaschool.recipeapp.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.stoyanvuchev.kodaschool.recipeapp.data.local.entity.RecipeEntity
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
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
    private lateinit var scope: TestScope
    private val recipeEntities = listOf(
        RecipeEntity(
            recipeId = "recipe_d04a8f9d3b6662a802c17e1a2e5f0f1b",
            isBookmarked = false,
            bookmarkTimestamp = null,
            label = "",
            category = RecipesCategory.Breakfast,
            source = "",
            ingredients = emptyList(),
            url = "",
            imageThumbnail = "",
            imageSmall = "",
            imageRegular = "",
            imageLarge = ""
        ),
        RecipeEntity(
            recipeId = "recipe_afe7f762a186efb95475c6c29d5da39b",
            isBookmarked = false,
            bookmarkTimestamp = null,
            label = "",
            category = RecipesCategory.Lunch,
            source = "",
            ingredients = emptyList(),
            url = "",
            imageThumbnail = "",
            imageSmall = "",
            imageRegular = "",
            imageLarge = ""
        ),
        RecipeEntity(
            recipeId = "recipe_452e9fce1999537b9e5698fda667c75f",
            isBookmarked = false,
            bookmarkTimestamp = null,
            label = "",
            category = RecipesCategory.Dinner,
            source = "",
            ingredients = emptyList(),
            url = "",
            imageThumbnail = "",
            imageSmall = "",
            imageRegular = "",
            imageLarge = ""
        )
    )

    @Before
    fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java
        ).build()
        dao = database.dao

        scope = TestScope(StandardTestDispatcher())
        scope.launch {
            dao.insertRecipes(recipeEntities)
        }

    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getRecipeById() = scope.runTest {

        val expected = recipeEntities.first()
        val actual = dao.getRecipeById(expected.recipeId)

        assertThat(actual).isEqualTo(expected)

    }

    @Test
    fun getRecipesByCategory() = scope.runTest {

        val category = RecipesCategory.Breakfast
        val expected = recipeEntities.filter { it.category == category }

        dao.getRecipesByCategory(category).test {
            assertThat(awaitItem()).isEqualTo(expected)
        }

    }

    @Test
    fun updateRecipeById() = scope.runTest {

        val expected = recipeEntities.first().copy(isBookmarked = true)
        dao.updateRecipeById(expected)

        val actual = dao.getRecipeById(expected.recipeId)
        assertThat(actual).isEqualTo(expected)

    }

}