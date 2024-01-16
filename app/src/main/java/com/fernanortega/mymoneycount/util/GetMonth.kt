package com.fernanortega.mymoneycount.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.fernanortega.mymoneycount.R
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.Month

@Composable
fun getMonth(): String =
    when (
        Clock.System.now().toLocalDateTime(
            TimeZone.currentSystemDefault()
        ).month
    ) {
        Month.JANUARY -> stringResource(id = R.string.january_label)
        Month.FEBRUARY -> stringResource(id = R.string.february_label)
        Month.MARCH -> stringResource(id = R.string.march_label)
        Month.APRIL -> stringResource(id = R.string.april_label)
        Month.MAY -> stringResource(id = R.string.may_label)
        Month.JUNE -> stringResource(id = R.string.june_label)
        Month.JULY -> stringResource(id = R.string.july_label)
        Month.AUGUST -> stringResource(id = R.string.august_label)
        Month.SEPTEMBER -> stringResource(id = R.string.september_label)
        Month.OCTOBER -> stringResource(id = R.string.october_label)
        Month.NOVEMBER -> stringResource(id = R.string.november_label)
        Month.DECEMBER -> stringResource(id = R.string.december_label)
    }