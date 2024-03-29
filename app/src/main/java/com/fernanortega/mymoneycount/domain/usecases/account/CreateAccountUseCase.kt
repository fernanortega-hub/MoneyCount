package com.fernanortega.mymoneycount.domain.usecases.account

import com.fernanortega.mymoneycount.domain.model.Account
import com.fernanortega.mymoneycount.domain.repository.AccountRepository

class CreateAccountUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(account: Account) =
        accountRepository.insertAccount(account)
}