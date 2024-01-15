package com.fernanortega.mymoneycount.presentation.ui.screens.currentregister

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.domain.util.RegisterType
import com.fernanortega.mymoneycount.util.toRegisterType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentRegisterScreen(
    modifier: Modifier = Modifier,
    uiState: CurrentRegisterUiState,
    onEvent: (CurrentRegisterEvent) -> Unit,
    onNavigateToCreateRegister: () -> Unit
) {
    val lazyColumnState = rememberLazyListState()
    val firstItemVisible by remember(lazyColumnState) {
        derivedStateOf {
            lazyColumnState.firstVisibleItemIndex == 0
        }
    }

    val pullToRefresh = rememberPullToRefreshState()
    LaunchedEffect(pullToRefresh.isRefreshing) {
        if(pullToRefresh.isRefreshing) {
            onEvent(CurrentRegisterEvent.Order(uiState.registerOrder))
        }
    }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = stringResource(id = R.string.create_register_label)
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null
                    )
                },
                onClick = onNavigateToCreateRegister,
                expanded = firstItemVisible
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
        ) {
            PullToRefreshContainer(state = pullToRefresh)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                state = lazyColumnState,
                contentPadding = PaddingValues(16.dp)
            ) {
                items(uiState.registers) { register ->
                    ListItem(
                        headlineContent = {
                            Text(
                                text = register.description
                            )
                        },
                        supportingContent = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                val chipColor = when (register.registerType.toRegisterType()) {
                                    RegisterType.EXPENSE -> Color.Cyan
                                    RegisterType.INCOME -> Color.Red
                                    RegisterType.SAVING -> Color.Green
                                    RegisterType.TRANSFER_IN -> Color.Blue
                                    RegisterType.TRANSFER_OUT -> Color.Yellow
                                    null -> Color.Gray
                                }
                                val text = stringResource(
                                    id = when (register.registerType.toRegisterType()) {
                                        RegisterType.EXPENSE -> R.string.expense_label
                                        RegisterType.INCOME -> R.string.income_label
                                        RegisterType.SAVING -> R.string.saving_label
                                        RegisterType.TRANSFER_IN -> R.string.transfer_in_label
                                        RegisterType.TRANSFER_OUT -> R.string.transfer_out_label
                                        null -> R.string.unknown_label
                                    }
                                )
                                Box(
                                    modifier = Modifier
                                        .background(chipColor, MaterialTheme.shapes.small)
                                        .padding(12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = text
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CurrentRegisterScreenPreview() {
    CurrentRegisterScreen(
        uiState = CurrentRegisterUiState(),
        onEvent = {

        },
        onNavigateToCreateRegister = {

        }
    )
}