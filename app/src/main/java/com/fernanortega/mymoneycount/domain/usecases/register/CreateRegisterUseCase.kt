package com.fernanortega.mymoneycount.domain.usecases.register

import com.fernanortega.mymoneycount.domain.model.Register
import com.fernanortega.mymoneycount.domain.repository.RegisterRepository

class CreateRegisterUseCase (
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(register: Register) {
        registerRepository.insertRegister(register)
    }
}