package com.fernanortega.mymoneycount.presentation.ui.screens.createaccount

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.util.CurrencyVisualTransformation

@Composable
fun CreateAccountDialog(
    modifier: Modifier = Modifier,
    uiState: CreateAccountState,
    onEvent: (CreateAccountEvent) -> Unit,
    onDismiss: () -> Unit,
) {
    val context = LocalContext.current
    Dialog(
        onDismissRequest = onDismiss
    ) {
        with(uiState) {
            LaunchedEffect(toast) {
                if (!toast.isNullOrBlank()) {
                    Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
                }
            }
            Column(
                modifier = modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceContainer,
                        MaterialTheme.shapes.extraLarge
                    )
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.create_account_label),
                    style = MaterialTheme.typography.headlineSmall
                )
                TextField(
                    value = accountName,
                    onValueChange = {
                        onEvent(
                            CreateAccountEvent.OnChangeAccountName(
                                it,
                                context
                            )
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.account_name_label)
                        )
                    },
                    isError = accountNameError != null,
                    supportingText = {
                        Text(
                            text = if (!accountNameError.isNullOrBlank()) {
                                accountNameError
                            } else {
                                stringResource(id = R.string.use_a_new_name_label)
                            }
                        )
                    },
                    singleLine = true
                )

                TextField(
                    value = currentBalance,
                    onValueChange = { value ->
                        if (value.all { it.isDigit() }) {
                            onEvent(
                                CreateAccountEvent.OnChangeRealBalance(
                                    value,
                                    context
                                )
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.balance_label)
                        )
                    },
                    isError = currentBalanceError != null,
                    supportingText = if (!currentBalanceError.isNullOrBlank()) {
                        {
                            Text(text = currentBalanceError)
                        }
                    } else null,
                    singleLine = true,
                    visualTransformation = CurrencyVisualTransformation()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = stringResource(id = R.string.cancel_label)
                        )
                    }
                    Button(
                        onClick = { onEvent(CreateAccountEvent.CreateAccount) },
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(text = stringResource(id = R.string.create_account_label))
                    }
                }
            }
        }
    }
}