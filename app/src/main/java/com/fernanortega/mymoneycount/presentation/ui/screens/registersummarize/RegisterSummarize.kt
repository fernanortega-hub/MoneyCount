package com.fernanortega.mymoneycount.presentation.ui.screens.registersummarize

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fernanortega.mymoneycount.presentation.navigation.Routes

fun NavGraphBuilder.registerSummarize() {
    composable(Routes.RegisterSummarize.route) {
        Text(
            text = "Register summarize"
        )
    }
}