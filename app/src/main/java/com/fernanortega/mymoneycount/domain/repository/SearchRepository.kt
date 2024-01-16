package com.fernanortega.mymoneycount.domain.repository

import com.fernanortega.mymoneycount.domain.model.RecentSearchQuery
import com.fernanortega.mymoneycount.domain.model.UserSearchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchContents(query: String): Flow<UserSearchResult>
    fun getRecentQueries(limit: Int): Flow<List<RecentSearchQuery>>

    suspend fun insertUpdateRecentQuery(query: String)

    suspend fun clearAllRecentQueries()
}