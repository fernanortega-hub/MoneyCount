package com.fernanortega.mymoneycount.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.domain.model.Account
import com.fernanortega.mymoneycount.util.toCurrency

@Composable
fun AccountItem(
    account: Account,
    onClick: () -> Unit,
) {
    ListItem(
        modifier = Modifier
            .clickable(
                role = Role.Button,
                onClick = onClick
            ),
        headlineContent = {
            Text(
                text = account.accountName
            )
        },
        supportingContent = {
            Text(
                text = stringResource(
                    R.string.current_balance_label,
                    account.currentBalance.toCurrency()
                )
            )
        }
    )
}