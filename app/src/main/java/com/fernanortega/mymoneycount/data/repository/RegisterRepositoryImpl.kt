package com.fernanortega.mymoneycount.data.repository

import com.fernanortega.mymoneycount.data.database.dao.RegisterDao
import com.fernanortega.mymoneycount.domain.model.Register
import com.fernanortega.mymoneycount.domain.repository.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerDao: RegisterDao
): RegisterRepository {

    override fun getRegistersByAccount(accountId: Int): Flow<List<Register>> {
        return registerDao.getRegistersByAccount(accountId).map { list ->
            list.map { registerAndAccount ->
                registerAndAccount.toModel()
            }
        }
    }

    override fun getRegistersByDate(start: Instant, end: Instant): Flow<List<Register>> {
        return registerDao.getRegistersByDate(start, end).map { list ->
            list.map { registerAndAccount ->
                registerAndAccount.toModel()
            }
        }
    }

    override suspend fun getRegisterById(id: Int): Register? = withContext(Dispatchers.IO) {
        return@withContext registerDao.getRegisterById(id)?.toModel()
    }

    override suspend fun insertRegister(register: Register) {
        withContext(Dispatchers.IO) {
            registerDao.insertRegister(register.toEntity())
        }
    }

    override suspend fun deleteRegister(register: Register) {
        withContext(Dispatchers.IO) {
            registerDao.deleteRegister(register.toEntity())
        }
    }
}