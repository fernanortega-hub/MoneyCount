package com.fernanortega.mymoneycount.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernanortega.mymoneycount.domain.usecases.register.RegisterUseCases
import com.fernanortega.mymoneycount.domain.usecases.register.util.RegisterOrder
import com.fernanortega.mymoneycount.presentation.ui.screens.registersummarize.RegisterSummarizeState
import com.fernanortega.mymoneycount.util.toInstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject
import kotlin.time.Duration.Companion.parseIsoString
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class RegisterSummarizeViewModel @Inject constructor(
    private val registerUseCases: RegisterUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterSummarizeState())
    val uiState = _uiState.asStateFlow()

    init {
        search()
    }

    fun onChangeDate(start: Long, end: Long) {
        _uiState.update { state ->
            state.copy(
                startDate = start.toInstant(),
                endDate = end.toInstant()
            )
        }

        search()
    }

    fun onChangeSortBy(sortBy: RegisterOrder) {
        if (
            _uiState.value.sortBy == sortBy &&
            _uiState.value.sortBy.orderType == sortBy.orderType
        ) return

        _uiState.update { state ->
            state.copy(
                sortBy = sortBy
            )
        }
        search()
    }

    private var searchJob: Job? = null
    private fun search() {
        searchJob?.cancel()
        registerUseCases
            .getRegisters(
                start = _uiState.value.startDate,
                end = _uiState.value.endDate.plus(parseIsoString(86_399.seconds.toIsoString())),
                registerOrder = _uiState.value.sortBy
            )
            .onEach { list ->
                _uiState.update { state ->
                    state.copy(
                        registers = list.groupBy { register ->
                            register.date.toLocalDateTime(TimeZone.UTC).month
                        }
                    )
                }
            }
            .launchIn(viewModelScope)
            .also { searchJob = it }
    }

    fun toggleDateRangeDialog(show: Boolean) {
        _uiState.update { state -> state.copy(showDateRangeDialog = show) }
    }

    fun toggleFilterDialogDialog(show: Boolean) {
        _uiState.update { state -> state.copy(showFilterDialog = show) }
    }
}
