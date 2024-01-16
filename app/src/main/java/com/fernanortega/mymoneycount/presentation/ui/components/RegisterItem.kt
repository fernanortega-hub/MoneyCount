package com.fernanortega.mymoneycount.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.domain.model.Account
import com.fernanortega.mymoneycount.domain.model.Register
import com.fernanortega.mymoneycount.domain.util.RegisterType
import com.fernanortega.mymoneycount.presentation.ui.theme.expenseContainerColor
import com.fernanortega.mymoneycount.presentation.ui.theme.expenseContentColor
import com.fernanortega.mymoneycount.presentation.ui.theme.incomeContainerColor
import com.fernanortega.mymoneycount.presentation.ui.theme.incomeContentColor
import com.fernanortega.mymoneycount.presentation.ui.theme.savingContainerColor
import com.fernanortega.mymoneycount.presentation.ui.theme.savingContentColor
import com.fernanortega.mymoneycount.presentation.ui.theme.transferInContainerColor
import com.fernanortega.mymoneycount.presentation.ui.theme.transferInContentColor
import com.fernanortega.mymoneycount.presentation.ui.theme.transferOutContainerColor
import com.fernanortega.mymoneycount.presentation.ui.theme.transferOutContentColor
import com.fernanortega.mymoneycount.util.toCurrency
import com.fernanortega.mymoneycount.util.toFormat
import com.fernanortega.mymoneycount.util.toRegisterType
import kotlinx.datetime.Clock

@Composable
fun RegisterItem(
    register: Register,
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier,
        headlineContent = {
            Text(
                text = register.description
            )
        },
        overlineContent = {
            Text(
                text = register.account.accountName
            )
        },
        trailingContent = {
            val registerType = register.registerType.toRegisterType()
            val container = when (registerType) {
                RegisterType.EXPENSE -> expenseContainerColor
                RegisterType.INCOME -> incomeContainerColor
                RegisterType.SAVING -> savingContainerColor
                RegisterType.TRANSFER_IN -> transferInContainerColor
                RegisterType.TRANSFER_OUT -> transferOutContainerColor
                null -> AssistChipDefaults.assistChipColors().containerColor
            }
            val contentColor = when (registerType) {
                RegisterType.EXPENSE -> expenseContentColor
                RegisterType.INCOME -> incomeContentColor
                RegisterType.SAVING -> savingContentColor
                RegisterType.TRANSFER_IN -> transferInContentColor
                RegisterType.TRANSFER_OUT -> transferOutContentColor
                null -> AssistChipDefaults.assistChipColors().labelColor
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.End
            ) {
                Box(
                    modifier = Modifier
                        .background(container, MaterialTheme.shapes.small)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(
                            id = when (registerType) {
                                RegisterType.EXPENSE -> R.string.expense_label
                                RegisterType.INCOME -> R.string.income_label
                                RegisterType.SAVING -> R.string.saving_label
                                RegisterType.TRANSFER_IN -> R.string.transfer_in_label
                                RegisterType.TRANSFER_OUT -> R.string.transfer_out_label
                                null -> R.string.unknown_label
                            }
                        ),
                        color = contentColor
                    )
                }
                Text(
                    text = register.date.toFormat()
                )
            }
        },
        supportingContent = {
            Text(
                text = register.amount.toCurrency()
            )
        }
    )
}

@Preview
@Composable
fun RegisterItemPreview() {
    MaterialTheme {
        Surface {
            RegisterItem(register = Register(
                id = 9808,
                date = Clock.System.now(),
                amount = 14.15,
                description = "tristique",
                registerType = 2,
                account = Account(
                    id = 3521,
                    currentBalance = 16.17,
                    realBalance = 18.19,
                    accountName = "Gilbert Burris"
                )
            )
            )
        }
    }
}