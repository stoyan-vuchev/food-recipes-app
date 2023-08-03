package com.stoyanvuchev.kodaschool.recipeapp

import assertk.assertThat
import assertk.assertions.isTrue
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.RemoteDataSourceAPI
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
    fun `RemoteDataSourceAPI returns RecipesSearchResponse`(): Unit = runBlocking {
        val response = api.searchRecipes(query = "breakfast")
        println("RemoteDataSourceAPITest: $response")
        assertThat(response.isSuccessful).isTrue()
    }

}