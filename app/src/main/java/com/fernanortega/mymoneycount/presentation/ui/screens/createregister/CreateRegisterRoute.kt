package com.fernanortega.mymoneycount.presentation.ui.screens.createregister

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fernanortega.mymoneycount.presentation.navigation.Routes
import com.fernanortega.mymoneycount.presentation.navigation.util.navigateToCreateAccount
import com.fernanortega.mymoneycount.presentation.viewmodels.CreateRegisterViewModel

fun NavGraphBuilder.createRegisterRoute(navController: NavHostController) {
    composable(Routes.CreateRegister.route) {
        val viewModel: CreateRegisterViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        CreateRegisterScreen(
            modifier = Modifier
                .fillMaxSize(),
            uiState = uiState,
            onEvent = viewModel::onEvent,
            onCancel = navController::popBackStack,
            onCreateAccount = navController::navigateToCreateAccount
        )
    }
}

