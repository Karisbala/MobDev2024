package com.example.shoppingapp.ui.feature

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.example.shoppingapp.ui.feature.cart.CartScreen
import com.example.shoppingapp.ui.feature.orders.OrdersScreen
import com.example.shoppingapp.ui.feature.products.ProductsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onLogout: () -> Unit) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf("Products") }

    ModalNavigationDrawer(
        drawerContent = {
            Surface(color = MaterialTheme.colorScheme.surface) {
                Box(modifier = Modifier.fillMaxWidth(0.3f)) {
                    Column {
                        NavigationDrawerItem(
                            label = { Text("Products") },
                            selected = (selectedItem == "Products"),
                            onClick = {
                                selectedItem = "Products"
                                scope.launch { drawerState.close() }
                                navController.navigate("products") {
                                    popUpTo("products") { inclusive = false }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        NavigationDrawerItem(
                            label = { Text("Orders") },
                            selected = (selectedItem == "Orders"),
                            onClick = {
                                selectedItem = "Orders"
                                scope.launch { drawerState.close() }
                                navController.navigate("orders") {
                                    popUpTo("products") { inclusive = false }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        NavigationDrawerItem(
                            label = { Text("Log Out") },
                            selected = (selectedItem == "Log Out"),
                            onClick = {
                                onLogout()
                                scope.launch { drawerState.close() }
                            }
                        )
                    }
                }
            }
        },
        drawerState = drawerState,
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(selectedItem) },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                }
            ) { padding ->
                NavHost(navController, startDestination = "products", modifier = Modifier.padding(padding)) {
                    composable("products") { ProductsScreen(onCartClick = { navController.navigate("cart") }) }
                    composable("orders") { OrdersScreen() }
                    composable("cart") { CartScreen(onOrderPlaced = { navController.navigate("orders") }) }
                }
            }
        }
    )
}