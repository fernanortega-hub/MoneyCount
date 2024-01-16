package com.fernanortega.mymoneycount.domain.model

import androidx.annotation.IntRange
import com.fernanortega.mymoneycount.data.database.entities.RegisterEntity
import kotlinx.datetime.Instant

data class Register(
    val id: Int,
    val date: Instant,
    val amount: Double,
    val description: String,
    @IntRange(from = 1, to = 5) val registerType: Int,
    val account: Account
) {
    fun toEntity(): RegisterEntity = RegisterEntity(
        registerId = id,
        date = date,
        amount = amount,
        description = description,
        registerType = registerType,
        accountOwnedId = account.id,
    )
}
