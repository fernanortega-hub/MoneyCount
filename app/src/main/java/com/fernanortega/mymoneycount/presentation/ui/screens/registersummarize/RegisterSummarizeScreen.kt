package com.fernanortega.mymoneycount.presentation.ui.screens.registersummarize

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.domain.usecases.register.util.RegisterOrder
import com.fernanortega.mymoneycount.presentation.ui.components.DateRangeDialog
import com.fernanortega.mymoneycount.presentation.ui.components.FilterRegisterDialog
import com.fernanortega.mymoneycount.presentation.ui.components.MyMoneyClickableTextField
import com.fernanortega.mymoneycount.presentation.ui.components.RegisterItem
import com.fernanortega.mymoneycount.util.getMonth
import com.fernanortega.mymoneycount.util.toFormat

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterSummarizeScreen(
    modifier: Modifier = Modifier,
    uiState: RegisterSummarizeState,
    onChangeDate: (Long, Long) -> Unit,
    toggleDateRangeDialog: (Boolean) -> Unit,
    toggleFilterDialogDialog: (Boolean) -> Unit,
    onChangeSortBy: (RegisterOrder) -> Unit
) {
    DateRangeDialog(
        show = uiState.showDateRangeDialog,
        onDismiss = { toggleDateRangeDialog(false) },
        onChangeDate = onChangeDate
    )

    FilterRegisterDialog(
        show = uiState.showFilterDialog,
        selectedSortBy = uiState.sortBy,
        onChangeSortBy = onChangeSortBy,
        onDismiss = { toggleFilterDialogDialog(false) },
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MyMoneyClickableTextField(
            value = uiState.startDate.toFormat() + " - " + uiState.endDate.toFormat(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = stringResource(id = R.string.date_range_label),
            onClick = {
                toggleDateRangeDialog(true)
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.filter_registers_label)
            )
            IconButton(onClick = { toggleFilterDialogDialog(true) }) {
                Icon(
                    imageVector = Icons.Rounded.FilterList,
                    contentDescription = null
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            uiState.registers.forEach { (month, registers) ->
                stickyHeader {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.monthly_registers_label,
                                getMonth(month)
                            )
                        )
                    }
                }
                items(
                    items = registers,
                    key = { it.id },
                    contentType = { it.id }
                ) { register ->
                    RegisterItem(register)
                    HorizontalDivider()
                }
            }
        }
    }
}

@Preview
@Composable
fun RegisterSummarizeScreenPreview() {
    MaterialTheme {
        RegisterSummarizeScreen(
            uiState = RegisterSummarizeState(),
            onChangeDate = { _, _ -> },
            toggleDateRangeDialog = {},
            toggleFilterDialogDialog = {},
            onChangeSortBy = {},

            )
    }
}