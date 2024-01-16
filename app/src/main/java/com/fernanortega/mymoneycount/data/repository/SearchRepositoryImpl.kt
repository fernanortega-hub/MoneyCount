package com.fernanortega.mymoneycount.data.repository

import com.fernanortega.mymoneycount.data.database.dao.AccountDao
import com.fernanortega.mymoneycount.data.database.dao.RecentSearchQueryDao
import com.fernanortega.mymoneycount.data.database.dao.RegisterDao
import com.fernanortega.mymoneycount.data.database.entities.RecentSearchQueryEntity
import com.fernanortega.mymoneycount.domain.model.RecentSearchQuery
import com.fernanortega.mymoneycount.domain.model.UserSearchResult
import com.fernanortega.mymoneycount.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock.*
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao,
    private val registerDao: RegisterDao,
    private val recentSearchQueryDao: RecentSearchQueryDao
) : SearchRepository {
    override fun searchContents(query: String): Flow<UserSearchResult> {
        val accountFlow = accountDao
            .searchAccounts("%$query%")
            .distinctUntilChanged()
        val registersWithAccountsFlow = registerDao
            .searchRegisters("%$query%")
            .distinctUntilChanged()

        return combine(accountFlow, registersWithAccountsFlow) { accounts, registers ->
            UserSearchResult(
                accounts = accounts.map { it.toModel() },
                registers = registers.map { it.toModel() }
            )
        }
    }

    override fun getRecentQueries(limit: Int): Flow<List<RecentSearchQuery>> {
        return recentSearchQueryDao
            .getRecentSearchQueries(limit)
            .distinctUntilChanged()
            .map { queries -> queries.map { it.toModel() } }
    }

    override suspend fun clearAllRecentQueries() = recentSearchQueryDao.clearRecentSearchQueries()

    override suspend fun insertUpdateRecentQuery(query: String) {
        recentSearchQueryDao.insertOrReplaceRecentSearchQuery(
            RecentSearchQueryEntity(
                query = query,
                queriedDate = System.now()
            )
        )
    }

}