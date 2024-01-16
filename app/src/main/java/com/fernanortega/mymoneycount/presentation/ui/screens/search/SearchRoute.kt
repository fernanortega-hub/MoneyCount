package com.fernanortega.mymoneycount.presentation.ui.screens.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fernanortega.mymoneycount.presentation.navigation.Routes

fun NavGraphBuilder.searchRoute(
    goBack: () -> Unit,
) {
    composable(Routes.Search.route) {
        SearchScreen()
    }
}