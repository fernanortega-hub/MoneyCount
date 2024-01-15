package com.fernanortega.mymoneycount.data.database.entities

import androidx.room.Embedded
import com.fernanortega.mymoneycount.domain.model.Register

data class AccountWithRegisters(
    @Embedded val accountEntity: AccountEntity,
    @Embedded val registerEntity: RegisterEntity
) {
    fun toModel(): Register = Register(
        id = registerEntity.registerId,
        date = registerEntity.date,
        amount = registerEntity.amount,
        description = registerEntity.description,
        registerType = registerEntity.registerType,
        account = accountEntity.toModel()
    )
}
