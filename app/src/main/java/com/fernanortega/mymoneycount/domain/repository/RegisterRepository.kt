package com.fernanortega.mymoneycount.domain.repository

import com.fernanortega.mymoneycount.domain.model.Register
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

interface RegisterRepository {

    fun getRegistersByAccount(accountId: Int): Flow<List<Register>>
    fun getRegistersByDate(start: Instant, end: Instant): Flow<List<Register>>

    suspend fun getRegisterById(id: Int): Register?

    suspend fun insertRegister(register: Register)

    suspend fun deleteRegister(register: Register)
}