package com.fernanortega.mymoneycount.domain.usecases.account

import com.fernanortega.mymoneycount.domain.model.Account
import com.fernanortega.mymoneycount.domain.repository.AccountRepository
import com.fernanortega.mymoneycount.domain.usecases.account.util.AccountValidator
import com.fernanortega.mymoneycount.domain.util.ValidatorResult

class CreateAccountUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(account: Account): ValidatorResult<AccountValidator> {
        val existingAccount = accountRepository.getAccountById(account.id)

        if (existingAccount?.accountName?.lowercase()
                ?.contains(account.accountName) == true || existingAccount != null
        ) {
            return ValidatorResult.Invalid(AccountValidator.EXISTING_ACCOUNT)
        }

        return ValidatorResult.Valid
    }
}