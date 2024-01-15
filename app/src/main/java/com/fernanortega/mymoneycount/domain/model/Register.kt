package com.fernanortega.mymoneycount.domain.model

import androidx.annotation.IntRange
import com.fernanortega.mymoneycount.data.database.entities.AccountWithRegisters
import com.fernanortega.mymoneycount.data.database.entities.RegisterEntity

data class Register(
    val id: Int,
    val date: Long,
    val amount: Double,
    val description: String,
    @IntRange(from = 1, to = 5) val registerType: Int,
    val account: Account
) {
    fun toEntity(): RegisterEntity = RegisterEntity(
        id = id,
        date = date,
        amount = amount,
        description = description,
        registerType = registerType,
        accountId = account.id,
    )

    fun toEntityPopulated(): AccountWithRegisters = AccountWithRegisters(
        registerEntity = toEntity(),
        accountEntity = account.toEntity()
    )
}
