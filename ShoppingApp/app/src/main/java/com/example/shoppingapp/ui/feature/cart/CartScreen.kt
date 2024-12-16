package com.example.shoppingapp.ui.feature.cart

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
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
    val context = LocalContext.current

    var showPlaceOrderDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.handleIntent(CartIntent.LoadCart)
    }

    LaunchedEffect(state.orderPlaced) {
        if (state.orderPlaced) {
            onOrderPlaced()
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Cart") }) },
        bottomBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Total Items: ${state.totalItems}")
                Text("Total Price: \$${state.totalPrice}")
                Button(
                    onClick = {
                        if (state.items.isEmpty()) {
                            Toast.makeText(context, "Cart is empty. Add items before ordering.", Toast.LENGTH_SHORT).show()
                        } else {
                            showPlaceOrderDialog = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Place Order")
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when {
                state.isLoading -> LoadingIndicator()
                state.error != null -> ErrorMessage(state.error!!)
                else -> {
                    if (state.items.isEmpty()) {
                        Text("Cart is empty", modifier = Modifier.align(Alignment.Center))
                    } else {
                        LazyColumn {
                            items(state.items) { item ->
                                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = item.productName,
                                                style = MaterialTheme.typography.titleMedium,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Text("Price: \$${item.price}")
                                            Text("Subtotal: \$${item.price * item.quantity}")
                                        }

                                        // Quantity controls
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            IconButton(onClick = { viewModel.handleIntent(CartIntent.DecreaseQuantity(item.productId)) }) {
                                                Text("-")
                                            }
                                            Text("${item.quantity}", modifier = Modifier.padding(horizontal = 8.dp))
                                            IconButton(onClick = { viewModel.handleIntent(CartIntent.IncreaseQuantity(item.productId)) }) {
                                                Text("+")
                                            }
                                            Spacer(modifier = Modifier.width(16.dp))
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
    if (showPlaceOrderDialog) {
        AlertDialog(
            onDismissRequest = { showPlaceOrderDialog = false },
            title = { Text("Confirm Order") },
            text = { Text("Are you sure you want to place this order?") },
            confirmButton = {
                TextButton(onClick = {
                    showPlaceOrderDialog = false
                    viewModel.handleIntent(CartIntent.PlaceOrder)
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showPlaceOrderDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}