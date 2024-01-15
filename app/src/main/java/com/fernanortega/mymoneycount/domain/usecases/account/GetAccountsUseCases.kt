package com.fernanortega.mymoneycount.domain.usecases.account

import com.fernanortega.mymoneycount.domain.model.Account
import com.fernanortega.mymoneycount.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

class GetAccountsUseCases(
    private val accountRepository: AccountRepository
) {
    operator fun invoke(): Flow<List<Account>> = accountRepository.getAccounts()
}
