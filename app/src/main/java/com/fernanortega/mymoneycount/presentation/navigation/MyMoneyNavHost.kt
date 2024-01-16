package com.fernanortega.mymoneycount.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.fernanortega.mymoneycount.presentation.MyMoneyAppState
import com.fernanortega.mymoneycount.presentation.navigation.util.navigateToCreateAccount
import com.fernanortega.mymoneycount.presentation.navigation.util.navigateToCreateRegister
import com.fernanortega.mymoneycount.presentation.ui.screens.account.accountsRoute
import com.fernanortega.mymoneycount.presentation.ui.screens.createaccount.createAccountRoute
import com.fernanortega.mymoneycount.presentation.ui.screens.createregister.createRegisterRoute
import com.fernanortega.mymoneycount.presentation.ui.screens.monthlyregister.monthlyRegisterRoute
import com.fernanortega.mymoneycount.presentation.ui.screens.registersummarize.registerSummarize
import com.fernanortega.mymoneycount.presentation.ui.screens.search.searchRoute

@Composable
fun MyMoneyNavHost(
    modifier: Modifier = Modifier,
    appState: MyMoneyAppState
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Routes.MonthlySummarize.route
    ) {
        monthlyRegisterRoute(
            onNavigateToCreateRegister = navController::navigateToCreateRegister
        )
        createRegisterRoute(
            goBack = navController::popBackStack,
            navigateToCreateAccount = navController::navigateToCreateAccount
        )
        createAccountRoute(goBack = navController::popBackStack)
        searchRoute(
            goBack = navController::popBackStack
        )
        accountsRoute()
        registerSummarize()
    }
}