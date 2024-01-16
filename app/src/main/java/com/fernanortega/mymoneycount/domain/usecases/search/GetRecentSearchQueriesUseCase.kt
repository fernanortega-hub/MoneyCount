package com.fernanortega.mymoneycount.domain.usecases.search

import com.fernanortega.mymoneycount.domain.model.RecentSearchQuery
import com.fernanortega.mymoneycount.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class GetRecentSearchQueriesUseCase(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(limit: Int = 10): Flow<List<RecentSearchQuery>> {
        return searchRepository.getRecentQueries(limit)
    }
}
