package com.fernanortega.mymoneycount.data.repository

import com.fernanortega.mymoneycount.data.database.dao.AccountDao
import com.fernanortega.mymoneycount.domain.model.Account
import com.fernanortega.mymoneycount.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao
): AccountRepository {
    override fun getAccounts(): Flow<List<Account>> {
        return accountDao.getAccounts().map { list ->
            list.map { accountEntity ->
                accountEntity.toModel()
            }
        }
    }

    override suspend fun deleteAccount(account: Account) {
        withContext(Dispatchers.IO) {
            accountDao.deleteAccount(account.toEntity())
        }
    }

    override suspend fun insertAccount(account: Account) {
        withContext(Dispatchers.IO) {
            accountDao.insertAccount(account.toEntity())
        }
    }

    override suspend fun updateAccount(account: Account) {
        withContext(Dispatchers.IO) {
            accountDao.updateAccount(account = account.toEntity())
        }
    }

    override suspend fun getAccountById(id: Int): Account? = withContext(Dispatchers.IO) {
            return@withContext accountDao.getAccountById(id)?.toModel()
    }
}