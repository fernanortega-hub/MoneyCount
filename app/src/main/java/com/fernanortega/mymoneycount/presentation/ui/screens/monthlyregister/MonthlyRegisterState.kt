package com.fernanortega.mymoneycount.presentation.ui.screens.monthlyregister

import com.fernanortega.mymoneycount.domain.model.Register
import com.fernanortega.mymoneycount.domain.usecases.register.util.RegisterOrder
import com.fernanortega.mymoneycount.util.OrderType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class MonthlyRegisterState(
    val registers: ImmutableList<Register> = persistentListOf(),
    val isLoading: Boolean = false,
    val message: String? = null,
    val registerOrder: RegisterOrder = RegisterOrder.Date(OrderType.DESCENDING),
    val showFilterDialog: Boolean = false
)
