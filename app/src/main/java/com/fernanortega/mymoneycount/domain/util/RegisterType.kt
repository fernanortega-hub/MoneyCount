package com.fernanortega.mymoneycount.domain.util

enum class RegisterType(val typeId: Int) {
    EXPENSE(1),
    INCOME(2),
    SAVING(3),
    TRANSFER_IN(4),
    TRANSFER_OUT(5)
}