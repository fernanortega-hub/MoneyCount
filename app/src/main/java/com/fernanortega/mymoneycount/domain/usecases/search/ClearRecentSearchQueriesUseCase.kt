package com.fernanortega.mymoneycount.domain.usecases.search

import com.fernanortega.mymoneycount.domain.repository.SearchRepository

class ClearRecentSearchQueriesUseCase(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke() = searchRepository.clearAllRecentQueries()
}
