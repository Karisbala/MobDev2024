package com.example.shoppingapp.ui.feature.cart

data class CartState(
    val isLoading: Boolean = false,
    val items: List<DetailedCartItem> = emptyList(),
    val error: String? = null,
    val orderPlaced: Boolean = false
) {
    val totalPrice: Double get() = items.sumOf { it.price * it.quantity }
    val totalItems: Int get() = items.sumOf { it.quantity }
}

data class DetailedCartItem(
    val productId: String,
    val productName: String,
    val price: Double,
    val quantity: Int
)