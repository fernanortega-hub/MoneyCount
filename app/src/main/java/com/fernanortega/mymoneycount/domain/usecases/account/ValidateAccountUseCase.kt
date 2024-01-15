package com.fernanortega.mymoneycount.domain.usecases.account

import com.fernanortega.mymoneycount.domain.model.Account
import com.fernanortega.mymoneycount.domain.repository.AccountRepository
import com.fernanortega.mymoneycount.domain.usecases.account.util.AccountValidator
import com.fernanortega.mymoneycount.domain.util.ValidatorResult

class ValidateAccountUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(account: Account?): ValidatorResult<AccountValidator> {
        if (account == null)
            return ValidatorResult.Invalid(AccountValidator.INVALID_ACCOUNT)

        accountRepository.getAccountById(account.id) ?: return ValidatorResult.Invalid(AccountValidator.NOT_EXISTING_ACCOUNT)

        return ValidatorResult.Valid
    }
}