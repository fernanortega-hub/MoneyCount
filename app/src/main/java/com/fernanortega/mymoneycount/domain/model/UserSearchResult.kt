package com.fernanortega.mymoneycount.domain.model

import androidx.compose.runtime.Stable

@Stable
data class UserSearchResult(
    val accounts: List<Account> = emptyList(),
    val registers: List<Register> = emptyList()
)
