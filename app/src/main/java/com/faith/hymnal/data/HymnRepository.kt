package com.faith.hymnal.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.faith.hymnal.data.local.Hymn
import com.faith.hymnal.data.local.HymnDao
import com.faith.hymnal.data.local.HymnDatabase
import kotlinx.coroutines.flow.Flow

class HymnRepository(private val hymnDao: HymnDao) {

    companion object {
        private const val PAGE_SIZE = 20

        @Volatile
        private var instance: HymnRepository? = null

        fun getInstance(database: HymnDatabase): HymnRepository {
            return instance ?: synchronized(this) {
                instance ?: HymnRepository(database.hymnDao()).also { instance = it }
            }
        }
    }

    fun getHymns(sortAlphabetical: Boolean = false, favoritesOnly: Boolean = false): Flow<PagingData<Hymn>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE * 2
            ),
            pagingSourceFactory = {
                when {
                    favoritesOnly -> hymnDao.getFavoriteHymnsPaging()
                    sortAlphabetical -> hymnDao.getAllHymnsAlphabeticalPaging()
                    else -> hymnDao.getAllHymnsPaging()
                }
            }
        ).flow
    }

    fun searchHymns(query: String): Flow<PagingData<Hymn>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { hymnDao.searchHymnsPaging(query) }
        ).flow
    }

    fun getHymnById(id: Int): Flow<Hymn?> = hymnDao.getHymnById(id)

    suspend fun toggleFavorite(hymn: Hymn) {
        hymnDao.setFavorite(hymn.id, !hymn.isFavorite)
    }

    suspend fun seedDatabaseIfEmpty(hymns: List<Hymn>) {
        if (hymnDao.getHymnCount() == 0) {
            hymnDao.insertAll(hymns)
        }
    }
}
