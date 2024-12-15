package com.example.shoppingapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppingapp.ui.feature.auth.login.LoginScreen
import com.example.shoppingapp.ui.feature.auth.register.RegisterScreen
import com.example.shoppingapp.ui.feature.products.ProductsScreen
import com.example.shoppingapp.ui.feature.cart.CartScreen
import com.example.shoppingapp.ui.feature.orders.OrdersScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("products") },
                onRegisterClick = { navController.navigate("register") }
            )
        }
        composable("register") {
            RegisterScreen(onRegisterSuccess = { navController.navigate("login") })
        }
        composable("products") {
            ProductsScreen(
                onCartClick = { navController.navigate("cart") }
            )
        }
        composable("cart") {
            CartScreen(
                onOrderPlaced = { navController.navigate("orders") }
            )
        }
        composable("orders") {
            OrdersScreen()
        }
    }
}