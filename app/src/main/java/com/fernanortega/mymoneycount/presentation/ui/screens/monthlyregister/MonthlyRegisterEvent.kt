package com.fernanortega.mymoneycount.presentation.ui.screens.monthlyregister

import com.fernanortega.mymoneycount.domain.model.Register
import com.fernanortega.mymoneycount.domain.usecases.register.util.RegisterOrder

sealed class MonthlyRegisterEvent {
    data class Order(val sortBy: RegisterOrder): MonthlyRegisterEvent()
    data class Delete(val register: Register): MonthlyRegisterEvent()
    data class OnFilterDialogChange(val show: Boolean): MonthlyRegisterEvent()
}