package com.fernanortega.mymoneycount.domain.usecases.search

data class SearchUseCases(
    val searchUseCase: SearchUseCase,
    val getRecentSearchQueriesUseCase: GetRecentSearchQueriesUseCase,
    val insertUpdateRecentSearchQueryUseCase: InsertUpdateRecentSearchQueryUseCase,
    val clearRecentSearchQueriesUseCase: ClearRecentSearchQueriesUseCase
)
