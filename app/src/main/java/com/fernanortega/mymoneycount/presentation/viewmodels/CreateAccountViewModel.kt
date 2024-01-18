package com.fernanortega.mymoneycount.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.domain.model.Account
import com.fernanortega.mymoneycount.domain.usecases.account.AccountUseCases
import com.fernanortega.mymoneycount.domain.usecases.account.util.AccountValidator
import com.fernanortega.mymoneycount.domain.util.ValidatorResult
import com.fernanortega.mymoneycount.presentation.ui.screens.createaccount.CreateAccountEvent
import com.fernanortega.mymoneycount.presentation.ui.screens.createaccount.CreateAccountState
import com.fernanortega.mymoneycount.util.transformLongToDoubleWithDecimals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateAccountState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: CreateAccountEvent) {
        when (event) {
            CreateAccountEvent.CreateAccount -> createAccount()
            is CreateAccountEvent.OnChangeAccountName -> validateAccountName(
                event.name,
                event.context
            )

            is CreateAccountEvent.OnChangeRealBalance -> onChangeRealBalance(event.amount)
        }
    }

    private var validateAccountNameJob: Job? = null
    private fun validateAccountName(name: String, context: Context) {
        val (_, accountName) = _uiState.updateAndGet { state -> state.copy(accountName = name) }

        validateAccountNameJob?.cancel()
        validateAccountNameJob = viewModelScope.launch {
            delay(300)
            when (
                val result = accountUseCases.createAccountValidator(
                    accountName = accountName.trim(),
                    accountId = 0
                )
            ) {
                is ValidatorResult.Invalid -> {
                    when (result.invalidResult) {
                        AccountValidator.EXISTING_ACCOUNT -> _uiState.update { state ->
                            state.copy(
                                accountNameError = context.getString(R.string.existing_account_label)
                            )
                        }

                        AccountValidator.SAME_ACCOUNT_NAME -> _uiState.update { state ->
                            state.copy(
                                accountNameError = context.getString(R.string.existing_account_with_same_name_label)
                            )
                        }

                        else -> Unit
                    }
                }

                ValidatorResult.Valid -> _uiState.update { state -> state.copy(accountNameError = null) }
            }
        }
    }

    private fun onChangeRealBalance(balance: String) {
        val formattedPrice = if (balance.startsWith("0")) "" else balance
        val isValidPrice =
            formattedPrice.ifBlank { "0" }.toLongOrNull().transformLongToDoubleWithDecimals()

        if (isValidPrice != null) {
            _uiState.update { state ->
                state.copy(
                    currentBalance = formattedPrice
                )
            }
        }
    }

    private var createAccountJob: Job? = null
    private fun createAccount() {
        if (createAccountJob != null || !_uiState.value.accountNameError.isNullOrBlank() || !_uiState.value.currentBalanceError.isNullOrBlank()) {
            return
        }

        createAccountJob = viewModelScope.launch {
            val balance =
                _uiState.value.currentBalance.ifBlank { "0" }.toLongOrNull().transformLongToDoubleWithDecimals() ?: 0.0
            val account = Account(
                id = 0,
                currentBalance = balance,
                realBalance = balance,
                accountName = _uiState.value.accountName.trim()
            )
            accountUseCases.createAccount(account)
            _uiState.update { state -> state.copy(isCreated = true) }
            createAccountJob = null
        }
    }
}
