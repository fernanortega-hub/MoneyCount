package com.fernanortega.mymoneycount.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Money
import androidx.compose.material.icons.rounded.Summarize
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.domain.usecases.register.util.RegisterOrder
import com.fernanortega.mymoneycount.util.OrderType

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterRegisterDialog(
    modifier: Modifier = Modifier,
    show: Boolean,
    selectedSortBy: RegisterOrder,
    onChangeSortBy: (RegisterOrder) -> Unit,
    onDismiss: () -> Unit
) {
    val configuration = LocalConfiguration.current
    if (show) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Column(
                modifier = modifier
                    .widthIn(
                        max = configuration.screenWidthDp.dp / 1.4f
                    )
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
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    maxItemsInEachRow = 100
                ) {
                    OrderType.entries.forEach { orderType ->
                        val (icon, label) = when (orderType) {
                            OrderType.ASCENDING -> Pair(
                                Icons.Rounded.ArrowUpward,
                                stringResource(id = R.string.ascending_label)
                            )

                            OrderType.DESCENDING -> Pair(
                                Icons.Rounded.ArrowDownward,
                                stringResource(id = R.string.descending_label)
                            )
                        }
                        val selected = orderType == selectedSortBy.orderType
                        FilterChip(
                            selected = selected,
                            onClick = { onChangeSortBy(selectedSortBy.changeOrderType(orderType)) },
                            label = {
                                Text(text = label)
                            },
                            leadingIcon = if (selected) {
                                {
                                    Icon(
                                        imageVector = Icons.Rounded.Check,
                                        contentDescription = null
                                    )
                                }
                            } else null,
                            trailingIcon = {
                                Icon(imageVector = icon, contentDescription = null)
                            }
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.sort_by),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    maxItemsInEachRow = 3
                ) {
                    val accountNameIsSelected = selectedSortBy is RegisterOrder.AccountName
                    FilterChip(
                        selected = accountNameIsSelected,
                        onClick = { onChangeSortBy(RegisterOrder.AccountName(selectedSortBy.orderType)) },
                        label = {
                            Text(text = stringResource(id = R.string.account_name_label))
                        },
                        leadingIcon = if (accountNameIsSelected) {
                            {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = null
                                )
                            }
                        } else null,
                        trailingIcon = {
                            Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = null)
                        }
                    )

                    val dateIsSelected = selectedSortBy is RegisterOrder.Date
                    FilterChip(
                        selected = dateIsSelected,
                        onClick = { onChangeSortBy(RegisterOrder.Date(selectedSortBy.orderType)) },
                        label = {
                            Text(text = stringResource(id = R.string.date_label))
                        },
                        leadingIcon = if (dateIsSelected) {
                            {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = null
                                )
                            }
                        } else null,
                        trailingIcon = {
                            Icon(imageVector = Icons.Rounded.DateRange, contentDescription = null)
                        }
                    )

                    val registerTypeIsSelected = selectedSortBy is RegisterOrder.RegisterType
                    FilterChip(
                        selected = registerTypeIsSelected,
                        onClick = { onChangeSortBy(RegisterOrder.RegisterType(selectedSortBy.orderType)) },
                        label = {
                            Text(text = stringResource(id = R.string.register_type_label))
                        },
                        leadingIcon = if (registerTypeIsSelected) {
                            {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = null
                                )
                            }
                        } else null,
                        trailingIcon = {
                            Icon(imageVector = Icons.Rounded.Summarize, contentDescription = null)
                        }
                    )

                    val amountIsSelected = selectedSortBy is RegisterOrder.Amount
                    FilterChip(
                        selected = amountIsSelected,
                        onClick = { onChangeSortBy(RegisterOrder.Amount(selectedSortBy.orderType)) },
                        label = {
                            Text(text = stringResource(id = R.string.amount_label))
                        },
                        leadingIcon = if (amountIsSelected) {
                            {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = null
                                )
                            }
                        } else null,
                        trailingIcon = {
                            Icon(imageVector = Icons.Rounded.Money, contentDescription = null)
                        }
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
        Surface {
            FilterRegisterDialog(
                show = true,
                selectedSortBy = RegisterOrder.Date(OrderType.ASCENDING),
                onChangeSortBy = {},
                onDismiss = {}
            )
        }
    }
}