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
fun OrdersScreen(
    viewModel: OrdersViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(OrdersIntent.LoadOrders)
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Orders") }) }) { padding ->
        when {
            state.isLoading -> LoadingIndicator()
            state.error != null -> ErrorMessage(state.error!!)
            else -> {
                LazyColumn(contentPadding = padding) {
                    items(state.orders) { order ->
                        OrderItemCard(order = order)
                    }
                }
            }
        }
    }
}