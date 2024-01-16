package com.fernanortega.mymoneycount.presentation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.fernanortega.mymoneycount.presentation.navigation.Routes
import com.fernanortega.mymoneycount.presentation.navigation.TopLevelDestination
import com.fernanortega.mymoneycount.presentation.navigation.util.navigateToAccounts
import com.fernanortega.mymoneycount.presentation.navigation.util.navigateToCurrentRegister
import com.fernanortega.mymoneycount.presentation.navigation.util.navigateToRegisterSummarize


@Composable
fun rememberMyMoneyAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
): MyMoneyAppState {
    return remember(
        navController,
        windowSizeClass
    ) {
        MyMoneyAppState(
            windowSizeClass = windowSizeClass,
            navController = navController
        )
    }
}

@Stable
class MyMoneyAppState(
    windowSizeClass: WindowSizeClass,
    val navController: NavHostController
) {
    val currentDestination: NavDestination? @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val destinations = TopLevelDestination.entries

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            Routes.MonthlySummarize.route -> TopLevelDestination.MONTHLY_SUMMARIZE
            Routes.Accounts.route -> TopLevelDestination.ACCOUNTS
            Routes.RegisterSummarize.route -> TopLevelDestination.REGISTER_SUMMARIZE
            else -> null
        }

    val shouldShowBottomBar = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (topLevelDestination) {
            TopLevelDestination.MONTHLY_SUMMARIZE -> navController.navigateToCurrentRegister(topLevelNavOptions)
            TopLevelDestination.ACCOUNTS -> navController.navigateToAccounts(topLevelNavOptions)
            TopLevelDestination.REGISTER_SUMMARIZE -> navController.navigateToRegisterSummarize(topLevelNavOptions)
        }
    }

    fun navigateToGlobalSearch() = navController.navigateToSearch()
}

private fun NavHostController.navigateToSearch() {
    this.navigate(Routes.Search.route)
}
