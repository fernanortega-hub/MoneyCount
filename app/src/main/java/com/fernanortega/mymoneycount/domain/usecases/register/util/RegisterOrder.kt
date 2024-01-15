package com.fernanortega.mymoneycount.domain.usecases.register.util

import com.fernanortega.mymoneycount.util.OrderType

sealed class RegisterOrder(val orderType: OrderType) {
    class Date(orderType: OrderType): RegisterOrder(orderType)
    class Amount(orderType: OrderType): RegisterOrder(orderType)
    class RegisterType(orderType: OrderType): RegisterOrder(orderType)
    class AccountName(orderType: OrderType): RegisterOrder(orderType)
}