package com.fernanortega.mymoneycount.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fernanortega.mymoneycount.data.database.entities.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Query("SELECT * FROM account_table ORDER BY current_balance DESC")
    fun getAccounts(): Flow<List<AccountEntity>>

    @Query("SELECT account_name FROM account_table")
    fun getAccountNames(): List<String>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAccount(accountEntity: AccountEntity)

    @Delete
    suspend fun deleteAccount(accountEntity: AccountEntity)

    @Update
    suspend fun updateAccount(account: AccountEntity)

    @Query("SELECT * FROM account_table WHERE account_id = :id")
    suspend fun getAccountById(id: Int): AccountEntity?

    @Query("SELECT * FROM account_table WHERE account_name LIKE :query")
    fun searchAccounts(query: String): Flow<List<AccountEntity>>

}