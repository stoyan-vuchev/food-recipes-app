package com.stoyanvuchev.kodaschool.recipeapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stoyanvuchev.kodaschool.recipeapp.domain.RecipesCategory
import com.stoyanvuchev.kodaschool.recipeapp.domain.model.RecipeIngredient

@Entity(tableName = "recipes_table")
data class RecipeEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "recipe_id")
    val recipeId: String,

    @ColumnInfo(name = "is_bookmarked")
    val isBookmarked: Boolean,
    @ColumnInfo(name = "bookmark_timestamp")
    val bookmarkTimestamp: Long?,

    val timestamp: Long,

    @ColumnInfo(name = "last_viewed_timestamp")
    val lastViewedTimestamp: Long?,

    val label: String,
    val category: RecipesCategory,
    val ingredients: List<RecipeIngredient>,
    val instructions: List<String>,
    val source: String,
    val url: String,

    val thumbnail: ByteArray?,
    val image: ByteArray?

) {

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RecipeEntity

        if (id != other.id) return false
        if (recipeId != other.recipeId) return false
        if (isBookmarked != other.isBookmarked) return false
        if (bookmarkTimestamp != other.bookmarkTimestamp) return false
        if (timestamp != other.timestamp) return false
        if (lastViewedTimestamp != other.lastViewedTimestamp) return false
        if (label != other.label) return false
        if (category != other.category) return false
        if (ingredients != other.ingredients) return false
        if (instructions != other.instructions) return false
        if (source != other.source) return false
        if (url != other.url) return false

        if (thumbnail != null) {
            if (other.thumbnail == null) return false
            if (!thumbnail.contentEquals(other.thumbnail)) return false
        } else if (other.thumbnail != null) return false

        if (image != null) {
            if (other.image == null) return false
            if (!image.contentEquals(other.image)) return false
        } else if (other.image != null) return false

        return true

    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + recipeId.hashCode()
        result = 31 * result + isBookmarked.hashCode()
        result = 31 * result + (bookmarkTimestamp?.hashCode() ?: 0)
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + (lastViewedTimestamp?.hashCode() ?: 0)
        result = 31 * result + label.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + ingredients.hashCode()
        result = 31 * result + instructions.hashCode()
        result = 31 * result + source.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + (thumbnail?.contentHashCode() ?: 0)
        result = 31 * result + (image?.contentHashCode() ?: 0)
        return result
    }

}