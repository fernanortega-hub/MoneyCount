package com.fernanortega.mymoneycount.domain.repository

import com.fernanortega.mymoneycount.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getAccounts(): Flow<List<Account>>
    suspend fun deleteAccount(account: Account)
    suspend fun insertAccount(account: Account)

    suspend fun updateAccount(account: Account)

    suspend fun getAccountById(id: Int): Account?

    suspend fun getAccountNames(): List<String>
}