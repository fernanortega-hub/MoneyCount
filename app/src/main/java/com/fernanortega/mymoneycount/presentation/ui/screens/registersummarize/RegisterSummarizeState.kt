package com.fernanortega.mymoneycount.presentation.ui.screens.registersummarize

import androidx.compose.runtime.Stable
import com.fernanortega.mymoneycount.domain.model.Register
import com.fernanortega.mymoneycount.domain.usecases.register.util.RegisterOrder
import com.fernanortega.mymoneycount.util.OrderType
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.Month
import kotlin.time.Duration

@Stable
data class RegisterSummarizeState(
    val registers: Map<Month, List<Register>> = emptyMap(),
    val startDate: Instant = Instant.fromEpochMilliseconds(Clock.System.now().minus(Duration.parse("24h")).toEpochMilliseconds()),
    val endDate: Instant = Clock.System.now(),
    val sortBy: RegisterOrder = RegisterOrder.Date(OrderType.ASCENDING),
    val showDateRangeDialog: Boolean = false,
    val showFilterDialog: Boolean = false
)
