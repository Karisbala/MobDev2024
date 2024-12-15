package com.example.shoppingapp.ui.feature.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.ui.components.ErrorMessage
import com.example.shoppingapp.ui.components.LoadingIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onOrderPlaced: () -> Unit,
    viewModel: CartViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(CartIntent.LoadCart)
    }

    if (state.orderPlaced) {
        onOrderPlaced()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Cart") }) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                content = { Text("Place Order") },
                onClick = { viewModel.handleIntent(CartIntent.PlaceOrder) }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when {
                state.isLoading -> LoadingIndicator()
                state.error != null -> ErrorMessage(state.error!!)
                else -> {
                    if (state.cartItems.isEmpty()) {
                        Text("Cart is empty", modifier = Modifier.align(Alignment.Center))
                    } else {
                        LazyColumn {
                            items(state.cartItems) { item ->
                                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text("Product: ${item.productId}, Qty: ${item.quantity}")
                                        TextButton(onClick = {
                                            viewModel.handleIntent(CartIntent.RemoveItem(item.productId))
                                        }) {
                                            Text("Remove")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}