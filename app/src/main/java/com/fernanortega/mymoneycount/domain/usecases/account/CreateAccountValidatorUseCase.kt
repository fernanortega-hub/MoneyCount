package com.fernanortega.mymoneycount.domain.usecases.account

import com.fernanortega.mymoneycount.domain.repository.AccountRepository
import com.fernanortega.mymoneycount.domain.usecases.account.util.AccountValidator
import com.fernanortega.mymoneycount.domain.util.ValidatorResult

class CreateAccountValidatorUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(accountName: String, accountId: Int): ValidatorResult<AccountValidator> {
        if (accountId != 0) {
            val existingAccount = accountRepository.getAccountById(accountId)

            if (existingAccount?.accountName?.lowercase()
                    ?.contains(accountName.lowercase()) == true || existingAccount != null
            ) {
                return ValidatorResult.Invalid(AccountValidator.EXISTING_ACCOUNT)
            }
        }

        val names = accountRepository.getAccountNames()

        return if(names.isEmpty()) {
            ValidatorResult.Valid
        } else {
            if(names.any { it.lowercase() == accountName.lowercase() }) {
                ValidatorResult.Invalid(AccountValidator.SAME_ACCOUNT_NAME)
            } else {
                ValidatorResult.Valid
            }
        }

    }
}