package com.fernanortega.mymoneycount.presentation.ui.screens.currentregister

import com.fernanortega.mymoneycount.domain.model.Register
import com.fernanortega.mymoneycount.domain.usecases.register.util.RegisterOrder

sealed class CurrentRegisterEvent {
    data class Order(val sortBy: RegisterOrder): CurrentRegisterEvent()
    data class Delete(val register: Register): CurrentRegisterEvent()
}