package com.fernanortega.mymoneycount.domain.usecases.register

import com.fernanortega.mymoneycount.domain.model.Register
import com.fernanortega.mymoneycount.domain.repository.AccountRepository
import com.fernanortega.mymoneycount.domain.repository.RegisterRepository
import com.fernanortega.mymoneycount.domain.util.RegisterType.EXPENSE
import com.fernanortega.mymoneycount.domain.util.RegisterType.INCOME
import com.fernanortega.mymoneycount.domain.util.RegisterType.SAVING
import com.fernanortega.mymoneycount.domain.util.RegisterType.TRANSFER_IN
import com.fernanortega.mymoneycount.domain.util.RegisterType.TRANSFER_OUT
import com.fernanortega.mymoneycount.util.toRegisterType

class CreateRegisterUseCase (
    private val registerRepository: RegisterRepository,
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(register: Register) {
        registerRepository.insertRegister(register)

        val modifiedAccount = register.account.copy(
            currentBalance = when(register.registerType.toRegisterType()) {
                EXPENSE, TRANSFER_OUT -> register.account.currentBalance - register.amount
                INCOME, SAVING, TRANSFER_IN -> register.account.currentBalance + register.amount
                null -> register.account.currentBalance
            }
        )

        accountRepository.updateAccount(modifiedAccount)
    }
}