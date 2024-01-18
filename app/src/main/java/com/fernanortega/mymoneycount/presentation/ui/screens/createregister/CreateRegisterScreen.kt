package com.fernanortega.mymoneycount.presentation.ui.screens.createregister

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.domain.util.RegisterType
import com.fernanortega.mymoneycount.presentation.ui.components.DatePickerDialog
import com.fernanortega.mymoneycount.presentation.ui.components.MyMoneyDropdownMenu
import com.fernanortega.mymoneycount.presentation.ui.screens.createregister.CreateRegisterEvent.CreateRegister
import com.fernanortega.mymoneycount.presentation.ui.screens.createregister.CreateRegisterEvent.OnChangeAccount
import com.fernanortega.mymoneycount.presentation.ui.screens.createregister.CreateRegisterEvent.OnChangeAmount
import com.fernanortega.mymoneycount.presentation.ui.screens.createregister.CreateRegisterEvent.OnChangeDate
import com.fernanortega.mymoneycount.presentation.ui.screens.createregister.CreateRegisterEvent.OnChangeDescription
import com.fernanortega.mymoneycount.presentation.ui.screens.createregister.CreateRegisterEvent.OnChangeRegisterType
import com.fernanortega.mymoneycount.presentation.ui.screens.createregister.CreateRegisterEvent.ToggleDatePicker
import com.fernanortega.mymoneycount.util.CurrencyVisualTransformation
import com.fernanortega.mymoneycount.util.addErrorChar
import com.fernanortega.mymoneycount.util.toFormat
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.Clock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRegisterScreen(
    modifier: Modifier = Modifier,
    uiState: CreateRegisterState,
    onEvent: (CreateRegisterEvent) -> Unit,
    onCancel: () -> Unit,
    onCreateAccount: () -> Unit
) {
    val context = LocalContext.current
    val topAppBarState = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollState = rememberScrollState()

    DatePickerDialog(
        show = uiState.showDatePicker,
        onDismiss = { onEvent(ToggleDatePicker) },
        onChangeDate = { date ->
            onEvent(OnChangeDate(date, context))
        },
        startDate = uiState.date.toEpochMilliseconds()
    )

    val dateMutableInteractionSource = remember {
        MutableInteractionSource()
    }

    val dateTextFieldIsPressed by dateMutableInteractionSource.collectIsPressedAsState()

    LaunchedEffect(dateTextFieldIsPressed) {
        if (dateTextFieldIsPressed) {
            onEvent(ToggleDatePicker)
        }
    }

    Scaffold(
        modifier = modifier
            .nestedScroll(topAppBarState.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                scrollBehavior = topAppBarState,
                title = {
                    Text(
                        text = stringResource(id = R.string.add_register_label)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onCancel
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = stringResource(id = R.string.go_back_label)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(scrollState)
                .nestedScroll(topAppBarState.nestedScrollConnection),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            with(uiState) {
                TextField(
                    value = description,
                    onValueChange = {
                        onEvent(
                            OnChangeDescription(
                                it, context
                            )
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    isError = descriptionError != null,
                    label = {
                        Text(
                            text = stringResource(id = R.string.description_label) + descriptionError.addErrorChar()
                        )
                    },
                    supportingText = if (descriptionError != null) {
                        {
                            Text(text = descriptionError)
                        }
                    } else null,
                    singleLine = true
                )

                TextField(
                    value = date.toFormat(),
                    onValueChange = { },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(ToggleDatePicker)
                        }
                        .zIndex(0f),
                    isError = dateError != null,
                    label = {
                        Text(
                            text = stringResource(id = R.string.date_label) + dateError.addErrorChar()
                        )
                    },
                    supportingText = if (dateError != null) {
                        {
                            Text(text = dateError)
                        }
                    } else null,
                    singleLine = true,
                    interactionSource = dateMutableInteractionSource
                )

                TextField(
                    value = amount,
                    onValueChange = { value ->
                        if (value.all { it.isDigit() }) {
                            onEvent(
                                OnChangeAmount(
                                    value, context
                                )
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isError = amountError != null,
                    prefix = {
                        Text(
                            text = "$"
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.amount_label) + amountError.addErrorChar()
                        )
                    },
                    supportingText = if (amountError != null) {
                        {
                            Text(text = amountError)
                        }
                    } else null,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    visualTransformation = CurrencyVisualTransformation())

                // Select account
                MyMoneyDropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth(),
                    options = availableAccounts.map { it.accountName }.toImmutableList(),
                    selectedItem = if (selectedAccount != null) availableAccounts.indexOf(
                        selectedAccount
                    ) else -1,
                    onSelectItem = { index ->
                        onEvent(OnChangeAccount(uiState.availableAccounts[index]))
                    },
                    error = accountError,
                    label = stringResource(id = R.string.accounts_label),
                    specialOption = {
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(id = R.string.create_account_label))
                            },
                            onClick = onCreateAccount,
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = {
                                Icon(imageVector = Icons.Rounded.Create, contentDescription = null)
                            }
                        )
                    }
                )
                MyMoneyDropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth(),
                    options = RegisterType.entries.map {
                        stringResource(
                            id = when (it) {
                                RegisterType.EXPENSE -> R.string.expense_label
                                RegisterType.INCOME -> R.string.income_label
                                RegisterType.SAVING -> R.string.saving_label
                                RegisterType.TRANSFER_IN -> R.string.transfer_in_label
                                RegisterType.TRANSFER_OUT -> R.string.transfer_out_label
                            }
                        )
                    }.toImmutableList(),
                    selectedItem = if (registerType != null) RegisterType.entries.indexOf(registerType) else -1,
                    onSelectItem = { index ->
                        onEvent(OnChangeRegisterType(RegisterType.entries[index]))
                    },
                    error = registerTypeError,
                    label = stringResource(id = R.string.register_type_label)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = onCancel
                    ) {
                        Text(text = stringResource(id = R.string.cancel_label))
                    }
                    Button(
                        modifier = Modifier
                            .weight(1f),
                        onClick = { onEvent(CreateRegister(context)) }
                    ) {
                        Text(text = stringResource(id = R.string.create_register_label))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateRegisterScreenDialogPreview() {
    CreateRegisterScreen(uiState = CreateRegisterState(
        date = Clock.System.now(),
        amount = "",
        description = "luctus",
        registerType = null,
        selectedAccount = null,
        availableAccounts = persistentListOf(),
        accountError = null,
        descriptionError = null,
        dateError = null,
        registerTypeError = null,
        amountError = null,
        toast = null
    ), onEvent = {},
        onCancel = {},
        onCreateAccount = {}
    )
}