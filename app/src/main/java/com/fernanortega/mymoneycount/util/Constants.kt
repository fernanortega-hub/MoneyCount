package com.fernanortega.mymoneycount.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object Constants {
    @OptIn(ExperimentalMaterial3Api::class)
    val selectableDatesUntilToday = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return (utcTimeMillis - 21_600_000) <= Clock.System.now().toEpochMilliseconds()
        }

        override fun isSelectableYear(year: Int): Boolean {
            return year <= Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year
        }
    }
}