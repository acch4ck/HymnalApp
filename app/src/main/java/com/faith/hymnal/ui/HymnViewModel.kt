package com.faith.hymnal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.faith.hymnal.HymnalApplication
import com.faith.hymnal.data.HymnData
import com.faith.hymnal.data.HymnRepository
import com.faith.hymnal.data.local.Hymn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class HymnViewModel(private val repository: HymnRepository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _sortAlphabetical = MutableStateFlow(false)
    val sortAlphabetical: StateFlow<Boolean> = _sortAlphabetical.asStateFlow()

    private val _favoritesOnly = MutableStateFlow(false)
    val favoritesOnly: StateFlow<Boolean> = _favoritesOnly.asStateFlow()

    val hymns: Flow<PagingData<Hymn>> = combine(
        _searchQuery, _sortAlphabetical, _favoritesOnly
    ) { query, sortAlpha, favOnly ->
        Triple(query, sortAlpha, favOnly)
    }.flatMapLatest { (query, sortAlpha, favOnly) ->
        if (query.isBlank()) {
            repository.getHymns(sortAlphabetical = sortAlpha, favoritesOnly = favOnly)
        } else {
            repository.searchHymns(query)
        }
    }.cachedIn(viewModelScope)

    private val _currentHymn = MutableStateFlow<Hymn?>(null)
    val currentHymn: StateFlow<Hymn?> = _currentHymn.asStateFlow()

    init {
        viewModelScope.launch {
            repository.seedDatabaseIfEmpty(HymnData.hymns)
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun toggleSort() {
        _sortAlphabetical.value = !_sortAlphabetical.value
    }

    fun toggleFavoritesOnly() {
        _favoritesOnly.value = !_favoritesOnly.value
    }

    fun loadHymn(id: Int) {
        viewModelScope.launch {
            repository.getHymnById(id).collect { hymn ->
                _currentHymn.value = hymn
            }
        }
    }

    fun toggleFavorite(hymn: Hymn) {
        viewModelScope.launch {
            repository.toggleFavorite(hymn)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                    as HymnalApplication
                HymnViewModel(application.repository)
            }
        }
    }
}
