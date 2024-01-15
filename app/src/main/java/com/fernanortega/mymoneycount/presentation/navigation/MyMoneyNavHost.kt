package com.fernanortega.mymoneycount.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.fernanortega.mymoneycount.presentation.MyMoneyAppState
import com.fernanortega.mymoneycount.presentation.ui.screens.createaccount.createAccountRoute
import com.fernanortega.mymoneycount.presentation.ui.screens.createregister.createRegisterRoute
import com.fernanortega.mymoneycount.presentation.ui.screens.currentregister.currentRegisterRoute

@Composable
fun MyMoneyNavHost(
    modifier: Modifier = Modifier,
    appState: MyMoneyAppState
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Routes.CurrentRegister.route
    ) {
        currentRegisterRoute(navController)
        createRegisterRoute(navController)
        createAccountRoute(navController)
    }
}
