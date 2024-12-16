package com.example.shoppingapp.ui.feature.orders

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.ui.components.ErrorMessage
import com.example.shoppingapp.ui.components.LoadingIndicator
import com.example.shoppingapp.ui.components.OrderItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(viewModel: OrdersViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    var orderToCancel by remember { mutableStateOf<String?>(null) }
    var showCancelDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.handleIntent(OrdersIntent.LoadOrders)
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Orders") }) }) { padding ->
        if (state.isLoading) {
            LoadingIndicator()
        } else if (state.error != null) {
            ErrorMessage(state.error!!)
        } else {
            LazyColumn(contentPadding = padding) {
                items(state.orders) { order ->
                    OrderItemCard(order = order, onCancelClick = {
                        orderToCancel = order.orderId
                        showCancelDialog = true
                    })
                }
            }
        }
    }

    if (showCancelDialog && orderToCancel != null) {
        AlertDialog(
            onDismissRequest = { showCancelDialog = false },
            title = { Text("Cancel Order") },
            text = { Text("Are you sure you want to cancel this order?") },
            confirmButton = {
                TextButton(onClick = {
                    showCancelDialog = false
                    orderToCancel?.let { viewModel.cancelOrder(it) }
                    orderToCancel = null
                }) {
                    Text("Yes, Cancel")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showCancelDialog = false
                    orderToCancel = null
                }) {
                    Text("No")
                }
            }
        )
    }
}