package com.fernanortega.mymoneycount.presentation.ui.screens.registersummarize

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fernanortega.mymoneycount.presentation.navigation.Routes
import com.fernanortega.mymoneycount.presentation.viewmodels.RegisterSummarizeViewModel

fun NavGraphBuilder.registerSummarize() {
    composable(Routes.RegisterSummarize.route) {
        val viewModel: RegisterSummarizeViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        RegisterSummarizeScreen(
            modifier = Modifier
                .fillMaxSize(),
            uiState = uiState,
            onChangeDate = viewModel::onChangeDate,
            toggleDateRangeDialog = viewModel::toggleDateRangeDialog,
            toggleFilterDialogDialog = viewModel::toggleFilterDialogDialog,
            onChangeSortBy = viewModel::onChangeSortBy,
        )
    }
}