package com.fernanortega.mymoneycount.data.database.entities

import androidx.annotation.IntRange
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(
    tableName = "register_table"
)
data class RegisterEntity(
    @PrimaryKey(autoGenerate = true) val registerId: Int,
    val date: Instant,
    val amount: Double,
    val description: String,
    @IntRange(from = 1, to = 5) @ColumnInfo("register_type") val registerType: Int,
    @ColumnInfo("account_owned_id") val accountOwnedId: Int
)
