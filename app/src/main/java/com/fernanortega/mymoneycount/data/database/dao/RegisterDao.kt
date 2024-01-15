package com.fernanortega.mymoneycount.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.fernanortega.mymoneycount.data.database.entities.AccountWithRegisters
import com.fernanortega.mymoneycount.data.database.entities.RegisterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RegisterDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertRegister(register: RegisterEntity)

    @Delete
    suspend fun deleteRegister(register: RegisterEntity)

    @Update
    suspend fun updateRegister(register: RegisterEntity)

    @Transaction
    @Query(
        "SELECT *, * FROM register_table " +
                "JOIN account_table ON account_table.id = account_id WHERE date IN (:start,:end)"
    )
    fun getRegistersByDate(start: Long, end: Long): Flow<List<AccountWithRegisters>>

    @Transaction
    @Query(
        "SELECT *, * FROM register_table " +
                "JOIN account_table ON account_table.id = account_id WHERE register_table.id LIKE :id"
    )
    suspend fun getRegisterById(id: Int): AccountWithRegisters?

    @Transaction
    @Query(
        "SELECT *, * FROM register_table " +
                "JOIN account_table ON account_table.id = account_id WHERE account_id LIKE :accountId"
    )
    fun getRegistersByAccount(accountId: Int): Flow<List<AccountWithRegisters>>
}