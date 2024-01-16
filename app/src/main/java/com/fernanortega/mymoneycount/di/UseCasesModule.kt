package com.fernanortega.mymoneycount.di

import com.fernanortega.mymoneycount.domain.repository.AccountRepository
import com.fernanortega.mymoneycount.domain.repository.RegisterRepository
import com.fernanortega.mymoneycount.domain.repository.SearchRepository
import com.fernanortega.mymoneycount.domain.usecases.account.AccountUseCases
import com.fernanortega.mymoneycount.domain.usecases.account.CreateAccountUseCase
import com.fernanortega.mymoneycount.domain.usecases.account.CreateAccountValidatorUseCase
import com.fernanortega.mymoneycount.domain.usecases.account.GetAccountsUseCases
import com.fernanortega.mymoneycount.domain.usecases.account.ValidateAccountUseCase
import com.fernanortega.mymoneycount.domain.usecases.register.CreateRegisterUseCase
import com.fernanortega.mymoneycount.domain.usecases.register.GetRegistersByDateUseCase
import com.fernanortega.mymoneycount.domain.usecases.register.RegisterUseCases
import com.fernanortega.mymoneycount.domain.usecases.register.ValidateCreateRegisterUseCase
import com.fernanortega.mymoneycount.domain.usecases.search.ClearRecentSearchQueriesUseCase
import com.fernanortega.mymoneycount.domain.usecases.search.GetRecentSearchQueriesUseCase
import com.fernanortega.mymoneycount.domain.usecases.search.InsertUpdateRecentSearchQueryUseCase
import com.fernanortega.mymoneycount.domain.usecases.search.SearchUseCase
import com.fernanortega.mymoneycount.domain.usecases.search.SearchUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun providesRegisterUseCases(
        accountRepository: AccountRepository,
        registerRepository: RegisterRepository
    ): RegisterUseCases = RegisterUseCases(
        createRegister = CreateRegisterUseCase(registerRepository, accountRepository),
        getRegisters = GetRegistersByDateUseCase(registerRepository),
        validateCreateRegister = ValidateCreateRegisterUseCase(accountRepository)
    )

    @Provides
    @Singleton
    fun providesAccountUseCases(
        accountRepository: AccountRepository
    ): AccountUseCases = AccountUseCases(
        createAccount = CreateAccountUseCase(accountRepository),
        validateAccountUseCase = ValidateAccountUseCase(accountRepository),
        createAccountValidator = CreateAccountValidatorUseCase(accountRepository),
        getAccounts = GetAccountsUseCases(accountRepository)
    )

    @Provides
    @Singleton
    fun providesSearchUseCases(
        searchRepository: SearchRepository
    ): SearchUseCases = SearchUseCases(
        searchUseCase = SearchUseCase(searchRepository),
        getRecentSearchQueriesUseCase = GetRecentSearchQueriesUseCase(searchRepository),
        insertUpdateRecentSearchQueryUseCase = InsertUpdateRecentSearchQueryUseCase(searchRepository),
        clearRecentSearchQueriesUseCase = ClearRecentSearchQueriesUseCase(searchRepository)
    )
}