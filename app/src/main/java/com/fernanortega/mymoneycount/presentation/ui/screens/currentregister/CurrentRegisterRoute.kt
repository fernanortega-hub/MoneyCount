package com.fernanortega.mymoneycount.presentation.ui.screens.currentregister

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fernanortega.mymoneycount.presentation.navigation.Routes
import com.fernanortega.mymoneycount.presentation.navigation.util.navigateToCreateRegister
import com.fernanortega.mymoneycount.presentation.viewmodels.CurrentRegisterViewModel

fun NavGraphBuilder.currentRegisterRoute(navController: NavHostController) {
    composable(
        route = Routes.CurrentRegister.route
    ) {
        val viewModel: CurrentRegisterViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        CurrentRegisterScreen(
            modifier = Modifier
                .fillMaxSize(),
            uiState = uiState,
            onEvent = viewModel::onEvent,
            onNavigateToCreateRegister = navController::navigateToCreateRegister
        )
    }
}