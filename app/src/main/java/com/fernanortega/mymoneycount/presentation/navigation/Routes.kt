package com.fernanortega.mymoneycount.presentation.navigation

sealed class Routes(val route: String) {
    data object MonthlySummarize: Routes("monthly_summarize")

    data object Accounts: Routes("accounts")
    data object RegisterSummarize: Routes("register_summarize")
    data object RegisterByAccount: Routes("registers_by_account/{$ACCOUNT_ID_KEY}") {
        fun createRoute(id: Int) = "registers_by_account/$id"
    }

    data object CreateRegister: Routes("create_register")
    data object CreateAccount: Routes("create_account")
    data object Search: Routes("search")

    companion object {
        const val ACCOUNT_ID_KEY = "account_id"
    }
}