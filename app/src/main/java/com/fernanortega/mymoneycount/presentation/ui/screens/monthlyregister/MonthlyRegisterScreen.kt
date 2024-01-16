package com.fernanortega.mymoneycount.presentation.ui.screens.monthlyregister

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.presentation.ui.components.FilterRegisterDialog
import com.fernanortega.mymoneycount.presentation.ui.components.RegisterItem
import com.fernanortega.mymoneycount.util.getMonth

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MonthlyRegisterScreen(
    modifier: Modifier = Modifier,
    uiState: MonthlyRegisterState,
    onEvent: (MonthlyRegisterEvent) -> Unit,
    onNavigateToCreateRegister: () -> Unit
) {
    val lazyColumnState = rememberLazyListState()

    FilterRegisterDialog(
        show = uiState.showFilterDialog,
        selectedSortBy = uiState.registerOrder,
        onChangeSortBy = { onEvent(MonthlyRegisterEvent.Order(it)) },
        onDismiss = {
            onEvent(MonthlyRegisterEvent.OnFilterDialogChange(false))
        }

    )

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCreateRegister
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.filter_label)
                )
                IconButton(onClick = { onEvent(MonthlyRegisterEvent.OnFilterDialogChange(true)) }) {
                    Icon(
                        imageVector = Icons.Rounded.FilterList,
                        contentDescription = null
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                state = lazyColumnState,
                contentPadding = PaddingValues(bottom = (16 + 56 + 16).dp)
            ) {
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
                                getMonth()
                            )
                        )
                    }
                }
                items(
                    items = uiState.registers,
                    key = { it.id },
                    contentType = { it.id },
                ) { register ->
                    RegisterItem(register)
                }
            }
        }
    }
}

@Preview
@Composable
fun MonthlyRegisterScreenPreview() {
    MonthlyRegisterScreen(
        uiState = MonthlyRegisterState(),
        onEvent = {

        },
        onNavigateToCreateRegister = {

        }
    )
}