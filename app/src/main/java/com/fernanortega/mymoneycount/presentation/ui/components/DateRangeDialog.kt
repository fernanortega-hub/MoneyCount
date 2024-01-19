package com.fernanortega.mymoneycount.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangeDialog(
    modifier: Modifier = Modifier,
    show: Boolean,
    onDismiss: () -> Unit,
    initialDate: Long? = null,
    endDate: Long? = null,
    onChangeDate: (start: Long, end: Long) -> Unit
) {
    if(show) {
        val dateRangePickerState = rememberDateRangePickerState(
            initialSelectedStartDateMillis = initialDate,
            initialSelectedEndDateMillis = endDate,
            selectableDates = Constants.selectableDatesUntilToday
        )

        LaunchedEffect(
            dateRangePickerState.selectedStartDateMillis,
            dateRangePickerState.selectedEndDateMillis
        ) {
            if (dateRangePickerState.selectedStartDateMillis != null && dateRangePickerState.selectedEndDateMillis != null) {
                onChangeDate(
                    dateRangePickerState.selectedStartDateMillis!!,
                    dateRangePickerState.selectedEndDateMillis!!
                )
            }
        }

        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Column(
                modifier = modifier
                    .widthIn(max = 460.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceContainerHigh,
                        MaterialTheme.shapes.extraLarge
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = stringResource(id = R.string.close_date_range)
                        )
                    }
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(text = stringResource(id = R.string.save_label))
                    }
                }
                DateRangePicker(
                    state = dateRangePickerState
                )
            }
        }
    }
}