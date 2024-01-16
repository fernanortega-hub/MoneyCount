package com.fernanortega.mymoneycount.presentation.ui.screens.createregister

import androidx.compose.runtime.Stable
import com.fernanortega.mymoneycount.domain.model.Account
import com.fernanortega.mymoneycount.domain.util.RegisterType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Stable
data class CreateRegisterState(
    val date: Instant = Clock.System.now(),
    val amount: String = "",
    val description: String = "",
    val registerType: RegisterType? = null,
    val selectedAccount: Account? = null,
    val availableAccounts: ImmutableList<Account> = persistentListOf(),
    val accountError: String? = null,
    val descriptionError: String? = null,
    val dateError: String? = null,
    val registerTypeError: String? = null,
    val amountError: String? = null,
    val toast: String? = null,
    val showDatePicker: Boolean = false,
    val isCreated: Boolean = false
)
