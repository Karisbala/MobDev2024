package com.example.shoppingapp.ui.feature.products

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.ui.components.ErrorMessage
import com.example.shoppingapp.ui.components.LoadingIndicator
import com.example.shoppingapp.ui.components.ProductItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    onCartClick: () -> Unit,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(ProductsIntent.LoadProducts)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Products") },
                actions = {
                    TextButton(onClick = onCartClick) {
                        Text("Cart")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                state.isLoading -> LoadingIndicator()
                state.error != null -> ErrorMessage(state.error!!)
                else -> {
                    LazyColumn {
                        items(state.products) { product ->
                            ProductItemCard(product = product) {
                                viewModel.handleIntent(ProductsIntent.AddToCart(product.productId))
                            }
                        }
                    }
                }
            }
        }
    }
}