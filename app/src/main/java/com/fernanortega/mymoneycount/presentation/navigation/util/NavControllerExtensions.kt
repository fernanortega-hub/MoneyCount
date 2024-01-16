package com.fernanortega.mymoneycount.presentation.navigation.util

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.fernanortega.mymoneycount.presentation.navigation.Routes
import com.fernanortega.mymoneycount.presentation.navigation.TopLevelDestination

fun NavHostController.navigateToCurrentRegister(navOptions: NavOptions? = null) {
    this.navigate(Routes.MonthlySummarize.route, navOptions)
}

fun NavHostController.navigateToCreateRegister(navOptions: NavOptions? = null) {
    this.navigate(Routes.CreateRegister.route, navOptions)
}

fun NavHostController.navigateToCreateAccount(navOptions: NavOptions? = null) {
    this.navigate(Routes.CreateAccount.route, navOptions)
}
fun NavHostController.navigateToRegisterSummarize(navOptions: NavOptions? = null) {
    this.navigate(Routes.RegisterSummarize.route, navOptions)
}

fun NavHostController.navigateToAccounts(navOptions: NavOptions? = null) {
    this.navigate(Routes.Accounts.route, navOptions)
}

fun NavHostController.navigateToRegistersByAccounts(
    accountId: Int,
    navOptions: NavOptions? = null
) {
    this.navigate(Routes.RegisterByAccount.createRoute(accountId), navOptions)
}

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name.lowercase(), true) ?: false
    } ?: this?.parent?.route?.contains(destination.name.lowercase()) ?: false