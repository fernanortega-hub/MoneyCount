package com.fernanortega.mymoneycount.data.database.entities

import androidx.annotation.IntRange
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "register_table"
)
data class RegisterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: Long,
    val amount: Double,
    val description: String,
    @IntRange(from = 1, to = 5) @ColumnInfo("register_type") val registerType: Int,
    @ColumnInfo("account_id") val accountId: Int
)
