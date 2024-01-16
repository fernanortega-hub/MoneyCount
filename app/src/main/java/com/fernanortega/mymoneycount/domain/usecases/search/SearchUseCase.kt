package com.fernanortega.mymoneycount.domain.usecases.search

import com.fernanortega.mymoneycount.domain.model.UserSearchResult
import com.fernanortega.mymoneycount.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCase(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(query: String): Flow<UserSearchResult> = searchRepository.searchContents(query)
}