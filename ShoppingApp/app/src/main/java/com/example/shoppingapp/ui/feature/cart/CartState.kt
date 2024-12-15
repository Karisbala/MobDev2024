package com.example.shoppingapp.ui.feature.cart

import com.example.shoppingapp.domain.model.CartItem

data class CartState(
    val isLoading: Boolean = false,
    val cartItems: List<CartItem> = emptyList(),
    val error: String? = null,
    val orderPlaced: Boolean = false
)