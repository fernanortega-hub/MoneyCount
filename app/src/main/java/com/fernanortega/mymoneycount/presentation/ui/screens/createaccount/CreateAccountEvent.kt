package com.fernanortega.mymoneycount.presentation.ui.screens.createaccount

import android.content.Context

sealed class CreateAccountEvent {
    data class OnChangeAccountName(val name: String, val context: Context): CreateAccountEvent()
    data class OnChangeRealBalance(val amount: String, val context: Context): CreateAccountEvent()
    data object CreateAccount: CreateAccountEvent()
}
