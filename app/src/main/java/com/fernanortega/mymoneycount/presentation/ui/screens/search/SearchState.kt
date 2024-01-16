package com.fernanortega.mymoneycount.presentation.ui.screens.search

import androidx.compose.runtime.Stable
import com.fernanortega.mymoneycount.domain.model.RecentSearchQuery
import com.fernanortega.mymoneycount.domain.model.UserSearchResult
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class SearchState(
    val query: String = "",
    val active: Boolean = false,
    val recentSearches: ImmutableList<RecentSearchQuery> = persistentListOf(),
    val results: UserSearchResult = UserSearchResult()
) {
    fun isEmpty(): Boolean = results.accounts.isEmpty() && results.registers.isEmpty()
}
