package com.fernanortega.mymoneycount.presentation.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fernanortega.mymoneycount.presentation.navigation.Routes
import com.fernanortega.mymoneycount.presentation.viewmodels.SearchViewModel

fun NavGraphBuilder.searchRoute(
    goBack: () -> Unit,
    onNavigateToAccount: (Int) -> Unit,
) {
    composable(Routes.Search.route) {
        val viewModel: SearchViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        SearchScreen(
            modifier = Modifier,
            uiState = uiState,
            onQueryChange = viewModel::onQueryChange,
            onBack = goBack,
            onNavigateToAccount = onNavigateToAccount,
            onActiveChange = viewModel::onActiveChange,
            onSearchExplicitly = viewModel::onSearchExplicitly,
            onClickQuery = viewModel::onClickQuery,
            clearRecentSearchQueries = viewModel::clearRecentSearches
        )
    }
}