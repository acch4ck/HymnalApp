package com.faith.hymnal.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HymnDao {

    @Query("SELECT * FROM hymns ORDER BY id ASC")
    fun getAllHymnsPaging(): PagingSource<Int, Hymn>

    @Query("SELECT * FROM hymns ORDER BY title ASC")
    fun getAllHymnsAlphabeticalPaging(): PagingSource<Int, Hymn>

    @Query("SELECT * FROM hymns WHERE isFavorite = 1 ORDER BY id ASC")
    fun getFavoriteHymnsPaging(): PagingSource<Int, Hymn>

    @Query("SELECT * FROM hymns WHERE title LIKE '%' || :query || '%' OR lyrics LIKE '%' || :query || '%' OR CAST(id AS TEXT) LIKE '%' || :query || '%' ORDER BY id ASC")
    fun searchHymnsPaging(query: String): PagingSource<Int, Hymn>

    @Query("SELECT * FROM hymns WHERE id = :id")
    fun getHymnById(id: Int): Flow<Hymn?>

    @Query("SELECT COUNT(*) FROM hymns")
    suspend fun getHymnCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hymns: List<Hymn>)

    @Update
    suspend fun updateHymn(hymn: Hymn)

    @Query("UPDATE hymns SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun setFavorite(id: Int, isFavorite: Boolean)
}
