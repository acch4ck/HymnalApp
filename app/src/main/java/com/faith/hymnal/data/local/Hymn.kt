package com.faith.hymnal.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hymns")
data class Hymn(
    @PrimaryKey val id: Int,           // hymn number
    val title: String,
    val author: String,
    val lyrics: String,                // verses separated by blank lines
    val category: String,
    val isFavorite: Boolean = false
) {
    val previewVerse: String
        get() = lyrics.split("\n\n").firstOrNull()?.replace("\n", " ")?.take(80) ?: ""
}
