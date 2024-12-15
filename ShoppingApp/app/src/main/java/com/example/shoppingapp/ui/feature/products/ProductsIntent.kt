package com.example.shoppingapp.ui.feature.products

sealed class ProductsIntent {
    data object LoadProducts : ProductsIntent()
    data class AddToCart(val productId: String) : ProductsIntent()
}