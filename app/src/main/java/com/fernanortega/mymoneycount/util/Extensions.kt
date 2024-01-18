package com.fernanortega.mymoneycount.util

import com.fernanortega.mymoneycount.domain.util.RegisterType
import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.text.NumberFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun Int.toRegisterType(): RegisterType? = when(this) {
    1 -> RegisterType.EXPENSE
    2 -> RegisterType.INCOME
    3 -> RegisterType.SAVING
    4 -> RegisterType.TRANSFER_IN
    5 -> RegisterType.TRANSFER_OUT
    else -> null
}

fun Long?.transformLongToDoubleWithDecimals(): Double? {
    return if(this == null) {
        null
    } else {
        val intPart = this / 100  // Divide por 100 para obtener la parte entera
        val decimalPart = (this % 100).toDouble() / 100  // Obtiene los últimos dos dígitos como decimal

        intPart + decimalPart
    }
}


fun Instant.toFormat(formatStyle: FormatStyle = FormatStyle.MEDIUM): String {
    return DateTimeFormatter
        .ofLocalizedDate(formatStyle)
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.of("GMT"))
        .format(this.toJavaInstant())
}

fun addErrorChar(error: String?): String {
    return if(error.isNullOrBlank()) "" else "*"
}

fun Double.toCurrency(): String {
    return NumberFormat.getCurrencyInstance().apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }.format(this)
}
