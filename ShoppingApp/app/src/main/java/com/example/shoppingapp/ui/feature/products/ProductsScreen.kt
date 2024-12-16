package com.example.shoppingapp.ui.feature.products

import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.ui.components.ErrorMessage
import com.example.shoppingapp.ui.components.LoadingIndicator
import com.example.shoppingapp.ui.components.ProductItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(onCartClick: () -> Unit, viewModel: ProductsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state.cartAddMessage) {
        val msg = state.cartAddMessage
        if (msg != null) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            viewModel.resetCartMessage()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.handleIntent(ProductsIntent.LoadProducts)
    }

    val filteredProducts = remember(state.products, state.selectedCategories) {
        if (state.selectedCategories.isEmpty()) {
            state.products
        } else {
            state.products.filter { it.category in state.selectedCategories }
        }
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
        if (state.isLoading) {
            LoadingIndicator()
        } else if (state.error != null) {
            ErrorMessage(state.error!!)
        } else {
            Column(modifier = Modifier.padding(padding)) {
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    state.categories.forEach { category ->
                        val selected = category.categoryId in state.selectedCategories
                        FilterChip(
                            selected = selected,
                            onClick = { viewModel.handleIntent(ProductsIntent.ToggleCategory(category.categoryId)) },
                            label = { Text(category.categoryName) },
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                LazyColumn {
                    items(filteredProducts) { product ->
                        ProductItemCard(product = product) {
                            viewModel.handleIntent(ProductsIntent.AddToCart(product.productId))
                        }
                    }
                }
            }
        }
    }
}