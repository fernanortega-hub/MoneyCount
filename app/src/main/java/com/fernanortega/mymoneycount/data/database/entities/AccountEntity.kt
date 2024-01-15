package com.fernanortega.mymoneycount.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fernanortega.mymoneycount.domain.model.Account

@Entity(tableName = "account_table")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val accountId: Int,
    @ColumnInfo("current_balance") val currentBalance: Double,
    @ColumnInfo("real_balance") val realBalance: Double,
    @ColumnInfo("account_name") val accountName: String
) {
    fun toModel(): Account = Account(
        id = accountId,
        currentBalance = currentBalance,
        realBalance = realBalance,
        accountName = accountName
    )
}
