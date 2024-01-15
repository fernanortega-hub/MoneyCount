package com.fernanortega.mymoneycount.domain.model

import com.fernanortega.mymoneycount.data.database.entities.AccountEntity

data class Account(
    val id: Int,
    val currentBalance: Double,
    val realBalance: Double,
    val accountName: String
) {
    fun toEntity(): AccountEntity = AccountEntity(
        accountId = id,
        currentBalance = currentBalance,
        realBalance = realBalance,
        accountName = accountName
    )
}
