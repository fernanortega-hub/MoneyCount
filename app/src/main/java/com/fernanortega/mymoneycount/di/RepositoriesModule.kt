package com.fernanortega.mymoneycount.di

import com.fernanortega.mymoneycount.data.repository.AccountRepositoryImpl
import com.fernanortega.mymoneycount.data.repository.RegisterRepositoryImpl
import com.fernanortega.mymoneycount.data.repository.SearchRepositoryImpl
import com.fernanortega.mymoneycount.domain.repository.AccountRepository
import com.fernanortega.mymoneycount.domain.repository.RegisterRepository
import com.fernanortega.mymoneycount.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {
    @Binds
    fun bindsAccountRepository(
        accountRepository: AccountRepositoryImpl,
    ): AccountRepository

    @Binds
    fun bindsRegisterRepository(
        registerRepository: RegisterRepositoryImpl,
    ): RegisterRepository

    @Binds
    fun bindsSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository
}