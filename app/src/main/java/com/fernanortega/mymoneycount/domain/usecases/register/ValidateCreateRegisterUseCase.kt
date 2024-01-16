package com.fernanortega.mymoneycount.domain.usecases.register

import com.fernanortega.mymoneycount.domain.model.Register
import com.fernanortega.mymoneycount.domain.repository.AccountRepository
import com.fernanortega.mymoneycount.domain.usecases.register.util.CreateRegisterValidator
import com.fernanortega.mymoneycount.domain.util.ValidatorResult
import kotlinx.datetime.Clock

class ValidateCreateRegisterUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(register: Register): ValidatorResult<CreateRegisterValidator> {
        accountRepository.getAccountById(register.account.id)
            ?: return ValidatorResult.Invalid(CreateRegisterValidator.NON_EXISTING_ACCOUNT)

        if(register.amount < 0.0) {
            return ValidatorResult.Invalid(CreateRegisterValidator.INVALID_AMOUNT)
        }

        if(register.description.isBlank()) {
            return ValidatorResult.Invalid(CreateRegisterValidator.INVALID_DESCRIPTION)
        }

        if(register.date > Clock.System.now()) {
            return ValidatorResult.Invalid(CreateRegisterValidator.INVALID_DATE)
        }

        return ValidatorResult.Valid
    }
}
