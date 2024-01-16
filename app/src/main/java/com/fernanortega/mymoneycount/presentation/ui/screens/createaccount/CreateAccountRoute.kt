package com.fernanortega.mymoneycount.presentation.ui.screens.createaccount

import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.fernanortega.mymoneycount.presentation.navigation.Routes
import com.fernanortega.mymoneycount.presentation.viewmodels.CreateAccountViewModel

fun NavGraphBuilder.createAccountRoute(
    goBack: () -> Unit
) {
    dialog(route = Routes.CreateAccount.route) {
        val viewModel: CreateAccountViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(uiState.isCreated) {
            if(uiState.isCreated) {
                goBack()
            }
        }
        CreateAccountDialog(
            modifier = Modifier
                .widthIn(max = 450.dp),
            uiState = uiState,
            onEvent = viewModel::onEvent,
            onDismiss = goBack
        )
    }
}