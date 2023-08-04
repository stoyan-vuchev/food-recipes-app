package com.stoyanvuchev.kodaschool.recipeapp.data.remote

import com.stoyanvuchev.kodaschool.recipeapp.BuildConfig
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.response.RecipeResponse
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.response.RecipeSearchResponse
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteDataSourceAPI {

    /**
     * Makes a [GET] request to the [/api/recipes/v2](-) API endpoint for recipes from a category.
     * @param type the type of recipes to search for. (public/user/any)
     * @param apiKey the key for the API endpoint.
     * @param appId the application ID for the API endpoint.
     * @param category the category of the recipes, e.g. [RecipesCategory.Lunch].
     * @param query the search text is the category name in that case.
     **/
    @GET("/api/recipes/v2")
    suspend fun searchByCategory(
        @Query("type") type: String = "public",
        @Query("app_key") apiKey: String = BuildConfig.REMOTE_DATA_SOURCE_API_KEY,
        @Query("app_id") appId: String = BuildConfig.REMOTE_DATA_SOURCE_API_APP_ID,
        @Query("mealType") category: String,
        @Query("q") query: String = category
    ): Response<RecipeSearchResponse>

    /**
     * Makes a [GET] request to the [/api/recipes/v2](-) API endpoint for recipes from a search query.
     * @param type the type of recipes to search for. (public/user/any)
     * @param apiKey the key for the API endpoint.
     * @param appId the application ID for the API endpoint.
     * @param query the search text, e.g. "chicken".
     **/
    @GET("/api/recipes/v2")
    suspend fun searchByQuery(
        @Query("type") type: String = "public",
        @Query("app_key") apiKey: String = BuildConfig.REMOTE_DATA_SOURCE_API_KEY,
        @Query("app_id") appId: String = BuildConfig.REMOTE_DATA_SOURCE_API_APP_ID,
        @Query("q") query: String
    ): Response<RecipeSearchResponse>

    /**
     * Makes a [GET] request to the [/api/recipes/v2/{id}](-) API endpoint for a specific recipe by ID.
     * @param recipeId the recipe ID.
     * @param type the type of recipe to search for.
     * @param apiKey the key for the API endpoint.
     * @param appId the application ID for the API endpoint.
     **/
    @GET("/api/recipes/v2/{id}")
    suspend fun getRecipeById(
        @Path("id") recipeId: String,
        @Query("type") type: String = "public",
        @Query("app_key") apiKey: String = BuildConfig.REMOTE_DATA_SOURCE_API_KEY,
        @Query("app_id") appId: String = BuildConfig.REMOTE_DATA_SOURCE_API_APP_ID
    ): Response<RecipeResponse>

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