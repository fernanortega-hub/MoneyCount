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
import kotlinx.datetime.Instant

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
        "SELECT * FROM register_table " +
                "INNER JOIN account_table ON account_table.account_id == account_owned_id " +
                "WHERE date BETWEEN :start AND :end"
    )
    fun getRegistersByDate(start: Instant, end: Instant): Flow<List<AccountWithRegisters>>

    @Transaction
    @Query(
        "SELECT *, * FROM register_table " +
                "INNER JOIN account_table ON account_table.account_id = account_owned_id WHERE register_table.registerId LIKE :id"
    )
    suspend fun getRegisterById(id: Int): AccountWithRegisters?

    @Transaction
    @Query(
        "SELECT *, * FROM register_table " +
                "JOIN account_table ON account_table.account_id = account_owned_id WHERE account_owned_id LIKE :accountId"
    )
    fun getRegistersByAccount(accountId: Int): Flow<List<AccountWithRegisters>>

    @Transaction
    @Query(
        "SELECT * FROM register_table " +
                "INNER JOIN account_table on account_table.account_id == account_owned_id " +
                "WHERE description LIKE :query OR account_name LIKE :query"
    )
    fun searchRegisters(query: String): Flow<List<AccountWithRegisters>>
}