package com.fernanortega.mymoneycount.domain.usecases.amount

import com.fernanortega.mymoneycount.domain.util.ValidatorResult

class AmountValidatorUseCase {
    operator fun invoke(amount: String): ValidatorResult<AmountValidator> {
        if(amount.any { it.isLetter() })
            return ValidatorResult.Invalid(AmountValidator.INVALID_AMOUNT)

        if(amount.filter { it == '.' }.length > 1)
            return ValidatorResult.Invalid(AmountValidator.INVALID_AMOUNT)

        amount.toDoubleOrNull()
            ?: return ValidatorResult.Invalid(AmountValidator.INVALID_AMOUNT)

        return ValidatorResult.Valid
    }
}

enum class AmountValidator {
    INVALID_AMOUNT
}