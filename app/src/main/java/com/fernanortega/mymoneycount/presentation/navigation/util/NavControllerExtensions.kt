package com.fernanortega.mymoneycount.presentation.navigation.util

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.fernanortega.mymoneycount.presentation.navigation.Routes

fun NavHostController.navigateToCurrentRegister(navOptions: NavOptions? = null) {
    this.navigate(Routes.CurrentRegister.route, navOptions)
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