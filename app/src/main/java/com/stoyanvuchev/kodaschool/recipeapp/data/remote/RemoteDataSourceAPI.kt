package com.stoyanvuchev.kodaschool.recipeapp.data.remote

import com.stoyanvuchev.kodaschool.recipeapp.BuildConfig
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.response.RecipeResponse
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.response.RecipeSearchResponse
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.response.SingleRecipeResponse
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteDataSourceAPI {

    @GET("/api/recipes/v2")
    suspend fun searchRecipesWithoutCategory(
        @Query("type") type: String = "public",
        @Query("app_key") apiKey: String = BuildConfig.REMOTE_DATA_SOURCE_API_KEY,
        @Query("app_id") appId: String = BuildConfig.REMOTE_DATA_SOURCE_API_APP_ID,
        @Query("q") query: String
    ): Response<RecipeSearchResponse>

    @GET("/api/recipes/v2")
    suspend fun searchRecipesWithCategory(
        @Query("type") type: String = "public",
        @Query("app_key") apiKey: String = BuildConfig.REMOTE_DATA_SOURCE_API_KEY,
        @Query("app_id") appId: String = BuildConfig.REMOTE_DATA_SOURCE_API_APP_ID,
        @Query("mealType") category: String,
        @Query("q") query: String = category
    ): Response<RecipeSearchResponse>

    @GET("/api/recipes/v2/{id}")
    suspend fun getRecipeById(
        @Path("id") recipeId: String,
        @Query("type") type: String = "public",
        @Query("app_key") apiKey: String = BuildConfig.REMOTE_DATA_SOURCE_API_KEY,
        @Query("app_id") appId: String = BuildConfig.REMOTE_DATA_SOURCE_API_APP_ID
    ): Response<SingleRecipeResponse>

    companion object {

        /** Creates an instance of the [RemoteDataSourceAPI]. */
        fun createInstance(): RemoteDataSourceAPI {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.REMOTE_DATA_SOURCE_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RemoteDataSourceAPI::class.java)
        }

    }

}