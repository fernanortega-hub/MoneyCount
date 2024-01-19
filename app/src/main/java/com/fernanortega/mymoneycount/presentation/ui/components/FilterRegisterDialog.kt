package com.fernanortega.mymoneycount.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.domain.usecases.register.util.RegisterOrder
import com.fernanortega.mymoneycount.util.OrderType

@Composable
fun FilterRegisterDialog(
    modifier: Modifier = Modifier,
    show: Boolean,
    selectedSortBy: RegisterOrder,
    onChangeSortBy: (RegisterOrder) -> Unit,
    onDismiss: () -> Unit
) {
    if (show) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .background(
                        MaterialTheme.colorScheme.surfaceContainer,
                        MaterialTheme.shapes.extraLarge
                    )
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = stringResource(id = R.string.order_label),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OrderType.entries.forEach { orderType ->
                        val label = when (orderType) {
                            OrderType.ASCENDING -> stringResource(id = R.string.ascending_label)
                            OrderType.DESCENDING -> stringResource(id = R.string.descending_label)
                        }
                        val selected = orderType == selectedSortBy.orderType
                        MyMoneyRadioButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            label = label,
                            selected = selected,
                            onClick = { onChangeSortBy(selectedSortBy.changeOrderType(orderType)) }
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.sort_registers_by),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val dateIsSelected = selectedSortBy is RegisterOrder.Date
                    MyMoneyRadioButton(
                        selected = dateIsSelected,
                        onClick = { onChangeSortBy(RegisterOrder.Date(selectedSortBy.orderType)) },
                        label = stringResource(id = R.string.date_label),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    val accountNameIsSelected = selectedSortBy is RegisterOrder.AccountName
                    MyMoneyRadioButton(
                        selected = accountNameIsSelected,
                        onClick = { onChangeSortBy(RegisterOrder.AccountName(selectedSortBy.orderType)) },
                        label = stringResource(id = R.string.account_name_label),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    val registerTypeIsSelected = selectedSortBy is RegisterOrder.RegisterType
                    MyMoneyRadioButton(
                        selected = registerTypeIsSelected,
                        onClick = { onChangeSortBy(RegisterOrder.RegisterType(selectedSortBy.orderType)) },
                        label = stringResource(id = R.string.register_type_label),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    val amountIsSelected = selectedSortBy is RegisterOrder.Amount
                    MyMoneyRadioButton(
                        selected = amountIsSelected,
                        onClick = { onChangeSortBy(RegisterOrder.Amount(selectedSortBy.orderType)) },
                        label = stringResource(id = R.string.amount_label),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Button(onClick = onDismiss) {
                    Text(
                        text = stringResource(id = R.string.done_label)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FilterRegisterDialogPreview() {
    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            FilterRegisterDialog(
                show = true,
                selectedSortBy = RegisterOrder.Date(OrderType.ASCENDING),
                onChangeSortBy = {},
                onDismiss = {}
            )
        }
    }
}