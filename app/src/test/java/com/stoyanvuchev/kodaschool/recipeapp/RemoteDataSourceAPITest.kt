package com.stoyanvuchev.kodaschool.recipeapp

import assertk.assertThat
import assertk.assertions.isTrue
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.RemoteDataSourceAPI
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RemoteDataSourceAPITest {

    private lateinit var api: RemoteDataSourceAPI

    @Before
    fun setUp() {
        api = RemoteDataSourceAPI.createInstance()
    }

    @Test
    fun `RemoteDataSourceAPI returns successful searchByCategory() response`(): Unit = runBlocking {
        val response = api.searchRecipesWithCategory(category = RecipesCategory.Default.stringValue)
        assertThat(response.isSuccessful).isTrue()
    }

    @Test
    fun `RemoteDataSourceAPI returns successful searchByQuery() response`(): Unit = runBlocking {
        val response = api.searchRecipesWithoutCategory(query = "Pancakes")
        assertThat(response.isSuccessful).isTrue()
    }

    @Test
    fun `RemoteDataSourceAPI returns successful getRecipeById() response`(): Unit = runBlocking {
        val response = api.getRecipeById(recipeId = "recipe_63ccc00c3c1051750933c9ad8fb9e987")
        assertThat(response.isSuccessful).isTrue()
    }

}