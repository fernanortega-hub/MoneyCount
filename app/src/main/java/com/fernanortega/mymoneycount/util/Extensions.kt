package com.fernanortega.mymoneycount.util

import com.fernanortega.mymoneycount.domain.util.RegisterType
import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
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

fun transformLongToDoubleWithDecimals(intValue: Long?): Double? {
    return if(intValue == null) {
        null
    } else {
        val intPart = intValue / 100  // Divide por 100 para obtener la parte entera
        val decimalPart = (intValue % 100).toDouble() / 100  // Obtiene los últimos dos dígitos como decimal

        intPart + decimalPart
    }
}

//fun transformDoubleToLong(double: Double): Long {
//    val factor = 100  // Factor para conservar dos decimales
//    return (double * factor).toLong()
//}


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
