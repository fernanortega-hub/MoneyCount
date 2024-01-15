package com.fernanortega.mymoneycount.presentation.ui.screens.createaccount

import androidx.compose.runtime.Stable

@Stable
data class CreateAccountState(
    val currentBalance: String = "",
    val accountName: String = "",
    val currentBalanceError: String? = null,
    val accountNameError: String? = null,
    val toast: String? = null,
    val isCreated: Boolean = false
)
