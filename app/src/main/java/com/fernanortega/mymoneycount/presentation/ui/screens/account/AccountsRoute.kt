package com.fernanortega.mymoneycount.presentation.ui.screens.account

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fernanortega.mymoneycount.presentation.navigation.Routes

fun NavGraphBuilder.accountsRoute() {
    composable(Routes.Accounts.route) {
        Text(text = "Accounts")
    }
}