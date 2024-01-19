package com.fernanortega.mymoneycount.domain.usecases.register

import com.fernanortega.mymoneycount.domain.model.Register
import com.fernanortega.mymoneycount.domain.repository.RegisterRepository
import com.fernanortega.mymoneycount.domain.usecases.register.util.RegisterOrder
import com.fernanortega.mymoneycount.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Instant

class GetRegistersByDateUseCase(
    private val registerRepository: RegisterRepository
) {
    operator fun invoke(
        start: Instant,
        end: Instant,
        registerOrder: RegisterOrder = RegisterOrder.Date(OrderType.DESCENDING)
    ): Flow<List<Register>> {
        return registerRepository.getRegistersByDate(start, end).map { list ->
            when(registerOrder.orderType) {
                OrderType.ASCENDING -> {
                    when(registerOrder) {
                        is RegisterOrder.AccountName -> list.sortedBy { it.account.accountName }
                        is RegisterOrder.Amount -> list.sortedBy { it.amount }
                        is RegisterOrder.Date -> list.sortedBy { it.date }
                        is RegisterOrder.RegisterType -> list.sortedBy { it.registerType }
                    }
                }
                OrderType.DESCENDING -> {
                    when(registerOrder) {
                        is RegisterOrder.AccountName -> list.sortedByDescending { it.account.accountName }
                        is RegisterOrder.Amount -> list.sortedByDescending { it.amount }
                        is RegisterOrder.Date -> list.sortedByDescending { it.date }
                        is RegisterOrder.RegisterType -> list.sortedByDescending { it.registerType }
                    }
                }
            }
        }
    }
}