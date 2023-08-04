package com.stoyanvuchev.kodaschool.recipeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stoyanvuchev.kodaschool.recipeapp.data.local.entity.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
@TypeConverters(LocalDatabaseTypeConverters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract val dao: LocalDatabaseDao
}