package com.fernanortega.mymoneycount.presentation.ui.screens.createaccount

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fernanortega.mymoneycount.presentation.navigation.Routes

fun NavGraphBuilder.createAccountRoute(navController: NavHostController) {
    composable(route = Routes.CreateAccount.route) {
        Text(text = "Create account")
        Button(onClick = navController::popBackStack) {
            Text(text = "Crear y regresar")
        }
    }
}