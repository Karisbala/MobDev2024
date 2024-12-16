package com.example.shoppingapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppingapp.ui.feature.MainScreen
import com.example.shoppingapp.ui.feature.auth.login.LoginScreen
import com.example.shoppingapp.ui.feature.auth.register.RegisterScreen

@Composable
fun AppNavigation(startDestination: String = "login") {
    val navController = rememberNavController()
    NavHost(navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("main") { popUpTo("login") { inclusive = true } } },
                onRegisterClick = { navController.navigate("register") }
            )
        }
        composable("register") {
            RegisterScreen(onRegisterSuccess = { navController.popBackStack() })
        }
        composable("main") {
            MainScreen(onLogout = {
                navController.navigate("login") {
                    popUpTo("main") { inclusive = true }
                }
            })
        }
    }
}