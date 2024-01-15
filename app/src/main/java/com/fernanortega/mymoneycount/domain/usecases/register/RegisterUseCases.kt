package com.fernanortega.mymoneycount.domain.usecases.register

data class RegisterUseCases(
    val createRegister: CreateRegisterUseCase,
    val getRegisters: GetRegistersByDateUseCase,
    val validateCreateRegister: ValidateCreateRegisterUseCase
)
