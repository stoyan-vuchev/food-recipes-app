package com.stoyanvuchev.kodaschool.recipeapp.di

import android.content.Context
import androidx.room.Room
import com.stoyanvuchev.kodaschool.recipeapp.data.local.LocalDatabase
import com.stoyanvuchev.kodaschool.recipeapp.data.preferences.Preferences
import com.stoyanvuchev.kodaschool.recipeapp.data.preferences.PreferencesImpl
import com.stoyanvuchev.kodaschool.recipeapp.data.preferences.PreferencesImpl.Companion.dataStore
import com.stoyanvuchev.kodaschool.recipeapp.data.remote.RemoteDataSourceAPI
import com.stoyanvuchev.kodaschool.recipeapp.data.repository.RecipesRepositoryImpl
import com.stoyanvuchev.kodaschool.recipeapp.domain.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideDataStorePreferences(@ApplicationContext context: Context): Preferences {
        return PreferencesImpl(context.dataStore)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSourceAPI(): RemoteDataSourceAPI {
        return RemoteDataSourceAPI.createInstance()
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java,
            "recipes_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecipesRepository(
        api: RemoteDataSourceAPI,
        db: LocalDatabase
    ): RecipesRepository {
        return RecipesRepositoryImpl(api, db.dao)
    }

}