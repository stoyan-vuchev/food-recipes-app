package com.stoyanvuchev.kodaschool.recipeapp.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.stoyanvuchev.kodaschool.recipeapp.data.local.entity.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
            spec = LocalDatabase.Companion.MigrationFrom1to2::class
        )
    ]
)
@TypeConverters(LocalDatabaseTypeConverters::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract val dao: LocalDatabaseDao

    companion object {

        @DeleteColumn.Entries(
            DeleteColumn(
                tableName = "recipes_table",
                columnName = "image_thumbnail"
            ),
            DeleteColumn(
                tableName = "recipes_table",
                columnName = "image_small"
            ),
            DeleteColumn(
                tableName = "recipes_table",
                columnName = "image_regular"
            ),
            DeleteColumn(
                tableName = "recipes_table",
                columnName = "image_large"
            )
        )
        class MigrationFrom1to2 : AutoMigrationSpec

    }

}