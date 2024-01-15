package com.fernanortega.mymoneycount.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernanortega.mymoneycount.domain.usecases.register.RegisterUseCases
import com.fernanortega.mymoneycount.domain.usecases.register.util.RegisterOrder
import com.fernanortega.mymoneycount.presentation.ui.screens.currentregister.CurrentRegisterEvent
import com.fernanortega.mymoneycount.presentation.ui.screens.currentregister.CurrentRegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class CurrentRegisterViewModel @Inject constructor(
    private val registerUseCases: RegisterUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(CurrentRegisterUiState())
    val uiState = _uiState.asStateFlow()

    private var getRegistersJob: Job? = null

    init {
        getNotes(_uiState.value.registerOrder)
    }

    fun onEvent(event: CurrentRegisterEvent) {
        when (event) {
            is CurrentRegisterEvent.Delete -> {
                viewModelScope.launch {
                    _uiState.update { state -> state.copy(isLoading = true) }
                    delay(400)
                    _uiState.update { state -> state.copy(isLoading = false) }
                }
            }

            is CurrentRegisterEvent.Order -> {
                if (_uiState.value.registerOrder == event.sortBy &&
                    _uiState.value.registerOrder.orderType == event.sortBy.orderType
                ) {
                    return
                }
                getNotes(event.sortBy)
            }
        }
    }

    private fun getNotes(order: RegisterOrder) {
        _uiState.update { state -> state.copy(isLoading = true) }
        getRegistersJob?.cancel()

        val timeZone = TimeZone.currentSystemDefault()
        val yearMonth = YearMonth.now()

        val startDate =
            yearMonth.atDay(1).atStartOfDay().toKotlinLocalDateTime().toInstant(timeZone)
                .toEpochMilliseconds()
        val endDate =
            yearMonth.atEndOfMonth().atTime(23, 59).toKotlinLocalDateTime().toInstant(timeZone)
                .toEpochMilliseconds()


        registerUseCases.getRegisters(
            start = startDate,
            end = endDate,
            registerOrder = order
        )
            .onEach { list ->
                _uiState.update { state ->
                    state.copy(
                        registers = list.toImmutableList()
                    )
                }
            }
            .launchIn(viewModelScope)
            .also { job -> getRegistersJob = job }

        _uiState.update { state -> state.copy(isLoading = false) }
    }


}