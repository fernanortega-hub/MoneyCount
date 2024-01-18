package com.fernanortega.mymoneycount.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.domain.model.Register
import com.fernanortega.mymoneycount.domain.usecases.account.AccountUseCases
import com.fernanortega.mymoneycount.domain.usecases.account.util.AccountValidator
import com.fernanortega.mymoneycount.domain.usecases.register.RegisterUseCases
import com.fernanortega.mymoneycount.domain.usecases.register.util.CreateRegisterValidator
import com.fernanortega.mymoneycount.domain.util.ValidatorResult
import com.fernanortega.mymoneycount.presentation.ui.screens.createregister.CreateRegisterEvent
import com.fernanortega.mymoneycount.presentation.ui.screens.createregister.CreateRegisterState
import com.fernanortega.mymoneycount.util.transformLongToDoubleWithDecimals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject

@HiltViewModel
class CreateRegisterViewModel @Inject constructor(
    private val registerUseCases: RegisterUseCases,
    private val accountUseCases: AccountUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(CreateRegisterState())
    val uiState = _uiState.asStateFlow()

    init {
        accountUseCases
            .getAccounts()
            .onEach {
                _uiState.update { state -> state.copy(availableAccounts = it.toImmutableList()) }
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: CreateRegisterEvent) {
        when (event) {
            CreateRegisterEvent.CreateAccount -> Unit
            is CreateRegisterEvent.OnChangeAccount -> {
                val account = _uiState.value.availableAccounts.find { it.id == event.account.id }
                if (account != null) {
                    _uiState.update { state -> state.copy(selectedAccount = account) }
                }
            }

            is CreateRegisterEvent.OnChangeAmount -> {
                val formattedPrice = if (event.amount.startsWith("0")) "" else event.amount
                val isValidPrice =
                    formattedPrice.ifBlank { "0" }.toLongOrNull().transformLongToDoubleWithDecimals()

                if (isValidPrice != null) {
                    _uiState.update { state ->
                        state.copy(
                            amount = formattedPrice
                        )
                    }
                }
            }

            is CreateRegisterEvent.OnChangeDate -> {
                val validInstant = Instant.fromEpochMilliseconds(event.date)

                if (validInstant > Clock.System.now()) {
                    _uiState.update { state ->
                        state.copy(
                            dateError = event.context.getString(R.string.invalid_date_label),
                            showDatePicker = false
                        )
                    }
                    return
                }

                _uiState.update { state ->
                    state.copy(
                        date = validInstant,
                        dateError = null,
                        showDatePicker = false
                    )
                }
            }

            is CreateRegisterEvent.OnChangeDescription -> {
                _uiState.update { state ->
                    state.copy(
                        description = event.description,
                        descriptionError = if (event.description.isBlank()) event.context.getString(
                            R.string.invalid_description_label
                        ) else null
                    )
                }
            }

            is CreateRegisterEvent.OnChangeRegisterType -> _uiState.update { state ->
                state.copy(
                    registerType = event.registerType
                )
            }

            is CreateRegisterEvent.CreateRegister -> createRegister(event.context)
            CreateRegisterEvent.ToggleDatePicker -> _uiState.update { state -> state.copy(showDatePicker = !state.showDatePicker) }
        }
    }

    private fun createRegister(context: Context) {
        viewModelScope.launch {
            try {
                restartErrors()

                val accountResult =
                    accountUseCases.validateAccountUseCase(_uiState.value.selectedAccount)

                when (accountResult) {
                    is ValidatorResult.Invalid -> {
                        when (accountResult.invalidResult) {
                            AccountValidator.NOT_EXISTING_ACCOUNT -> _uiState.update {
                                it.copy(
                                    accountError = context.getString(R.string.account_not_exist_label)
                                )
                            }

                            AccountValidator.INVALID_ACCOUNT -> _uiState.update {
                                it.copy(
                                    accountError = context.getString(
                                        R.string.select_an_account_label
                                    )
                                )
                            }
                            else -> Unit
                        }
                        return@launch
                    }

                    ValidatorResult.Valid -> Unit
                }

                if (_uiState.value.registerType == null) {
                    _uiState.update {
                        it.copy(
                            registerTypeError = context.getString(R.string.select_a_register_type_label)
                        )
                    }
                    return@launch
                }

                val validRegister = validateRegister(
                    context, Register(
                        id = 0,
                        date = _uiState.value.date,
                        amount = _uiState.value.amount.toLongOrNull().transformLongToDoubleWithDecimals() ?: 0.0,
                        description = _uiState.value.description,
                        registerType = _uiState.value.registerType!!.typeId,
                        account = _uiState.value.selectedAccount!!
                    )
                ) ?: return@launch

                registerUseCases.createRegister(validRegister)
                _uiState.update { state -> state.copy(isCreated = true) }
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(
                        toast = e.message.toString()
                    )
                }
            }
        }
    }

    private suspend fun validateRegister(
        context: Context,
        register: Register
    ): Register? {
        when (val result = registerUseCases.validateCreateRegister(register)) {
            is ValidatorResult.Invalid -> {
                when (result.invalidResult) {
                    CreateRegisterValidator.NON_EXISTING_ACCOUNT -> _uiState.update {
                        it.copy(
                            accountError = context.getString(R.string.account_not_exist_label)
                        )
                    }

                    CreateRegisterValidator.INVALID_DATE -> _uiState.update {
                        it.copy(
                            dateError = context.getString(R.string.invalid_date_label)
                        )
                    }

                    CreateRegisterValidator.INVALID_DESCRIPTION -> _uiState.update {
                        it.copy(
                            accountError = context.getString(R.string.invalid_description_label)
                        )
                    }

                    CreateRegisterValidator.INVALID_AMOUNT -> _uiState.update {
                        it.copy(
                            accountError = context.getString(R.string.invalid_amount_label)
                        )
                    }
                }
                return null
            }

            ValidatorResult.Valid -> return register
        }
    }

    private fun restartErrors() {
        _uiState.update { state ->
            state.copy(
                dateError = null,
                accountError = null,
                amountError = null,
                registerTypeError = null,
                descriptionError = null
            )
        }
    }

}
