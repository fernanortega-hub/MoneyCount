package com.fernanortega.mymoneycount.presentation.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.presentation.ui.theme.MyMoneyCountTheme
import com.fernanortega.mymoneycount.util.Constants
import kotlinx.datetime.Clock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    startDate: Long = Clock.System.now().toEpochMilliseconds(),
    onChangeDate: (Long) -> Unit
) {
    if(show) {
        val state = rememberDatePickerState(
            initialSelectedDateMillis = startDate,
            selectableDates = Constants.selectableDatesUntilToday
        )
        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(
                    onClick = {
                        state.selectedDateMillis?.let { onChangeDate(it) }
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.save_label)
                    )
                }
            }
        ) {
            DatePicker(
                state = state
            )
        }
    }
}

@Preview
@Composable
fun DatePickerDialogPreview() {
    MyMoneyCountTheme {
        Surface {
            DatePickerDialog(show = true, onDismiss = { /*TODO*/ }, onChangeDate = {})
        }
    }
}