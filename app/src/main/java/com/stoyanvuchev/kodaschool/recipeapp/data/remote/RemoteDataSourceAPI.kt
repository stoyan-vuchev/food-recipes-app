package com.stoyanvuchev.kodaschool.recipeapp.data.remote

import com.stoyanvuchev.kodaschool.recipeapp.BuildConfig
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.response.RecipeSearchResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSourceAPI {

    @GET("/api/recipes/v2")
    suspend fun searchRecipes(
        @Query("type") type: String = "public",
        @Query("app_key") apiKey: String = BuildConfig.REMOTE_DATA_SOURCE_API_KEY,
        @Query("app_id") appId: String = BuildConfig.REMOTE_DATA_SOURCE_API_APP_ID,
        @Query("q") query: String
    ): Response<RecipeSearchResponse>

    companion object {

        fun createInstance(): RemoteDataSourceAPI {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.REMOTE_DATA_SOURCE_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RemoteDataSourceAPI::class.java)
        }

    }

}