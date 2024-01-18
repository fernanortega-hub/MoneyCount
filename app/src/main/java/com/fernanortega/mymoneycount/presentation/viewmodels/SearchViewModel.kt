package com.fernanortega.mymoneycount.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernanortega.mymoneycount.domain.usecases.search.SearchUseCases
import com.fernanortega.mymoneycount.presentation.ui.screens.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchState())
    val uiState = _uiState.asStateFlow()

    init {
        searchUseCases
            .getRecentSearchQueriesUseCase()
            .onEach { list ->
                _uiState.update { state -> state.copy(recentSearches = list.toImmutableList()) }
            }
            .launchIn(viewModelScope)
    }

    fun onQueryChange(query: String) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(query = query) }
            if (query.trim().isBlank() || query.trim().length < 2) return@launch
            delay(250)
            search()
        }
    }

    private var searchJob: Job? = null
    private fun search() {
        searchJob?.cancel()
        searchUseCases.searchUseCase(_uiState.value.query)
            .onEach { userSearchResult ->
                _uiState.update { state -> state.copy(results = userSearchResult) }
            }
            .launchIn(viewModelScope)
            .also { searchJob = it }
    }

    fun onActiveChange(active: Boolean) {
        _uiState.update { it.copy(active = active) }
    }


    fun onSearchExplicitly(query: String) {
        viewModelScope.launch {
            searchUseCases.insertUpdateRecentSearchQueryUseCase(query)
            _uiState.update { state -> state.copy(active = false, query = query.trim()) }
        }
    }

    fun onClickQuery(query: String) {
        _uiState.update { state ->
            state.copy(
                active = false,
                query = query
            )
        }
        search()
    }

    fun clearRecentSearches() {
        viewModelScope.launch {
            searchUseCases.clearRecentSearchQueriesUseCase()
        }
    }
}