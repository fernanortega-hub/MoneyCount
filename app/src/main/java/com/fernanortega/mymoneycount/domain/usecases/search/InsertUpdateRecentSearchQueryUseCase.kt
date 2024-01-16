package com.fernanortega.mymoneycount.domain.usecases.search

import com.fernanortega.mymoneycount.domain.repository.SearchRepository

class InsertUpdateRecentSearchQueryUseCase(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String) =
        searchRepository.insertUpdateRecentQuery(query)
}
