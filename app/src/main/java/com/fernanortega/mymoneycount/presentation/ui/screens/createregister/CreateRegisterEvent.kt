package com.fernanortega.mymoneycount.presentation.ui.screens.createregister

import android.content.Context
import com.fernanortega.mymoneycount.domain.model.Account
import com.fernanortega.mymoneycount.domain.util.RegisterType

sealed class CreateRegisterEvent {
    data class OnChangeAmount(val amount: String, val context: Context): CreateRegisterEvent()
    data class OnChangeRegisterType(val registerType: RegisterType): CreateRegisterEvent()
    data class OnChangeAccount(val account: Account): CreateRegisterEvent()
    data class OnChangeDescription(val description: String, val context: Context): CreateRegisterEvent()
    data class OnChangeDate(val date: Long, val context: Context): CreateRegisterEvent()
    data class CreateRegister(val context: Context): CreateRegisterEvent()
    data object CreateAccount: CreateRegisterEvent()
    data object ToggleDatePicker: CreateRegisterEvent()
}
