package com.fernanortega.mymoneycount.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.fernanortega.mymoneycount.domain.model.Register

data class AccountWithRegisters(
    @Embedded val accountEntity: AccountEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "account_id"
    )
    val registerEntity: RegisterEntity,
) {
    fun toModel(): Register = Register(
        id = registerEntity.id,
        date = registerEntity.date,
        amount = registerEntity.amount,
        description = registerEntity.description,
        registerType = registerEntity.registerType,
        account = accountEntity.toModel()
    )
}
