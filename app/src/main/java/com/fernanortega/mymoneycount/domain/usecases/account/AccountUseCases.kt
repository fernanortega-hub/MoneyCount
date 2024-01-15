package com.fernanortega.mymoneycount.domain.usecases.account

data class AccountUseCases(
    val createAccount: CreateAccountUseCase,
    val validateAccountUseCase: ValidateAccountUseCase,
    val createAccountValidator: CreateAccountValidatorUseCase,
    val getAccounts: GetAccountsUseCases
)
