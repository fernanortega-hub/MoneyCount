package com.fernanortega.mymoneycount.domain.repository

import com.fernanortega.mymoneycount.domain.model.Register
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {

    fun getRegistersByAccount(accountId: Int): Flow<List<Register>>
    fun getRegistersByDate(start: Long, end: Long): Flow<List<Register>>

    suspend fun getRegisterById(id: Int): Register?

    suspend fun insertRegister(register: Register)

    suspend fun deleteRegister(register: Register)
}