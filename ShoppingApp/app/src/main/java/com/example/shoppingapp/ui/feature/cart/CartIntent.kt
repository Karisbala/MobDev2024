package com.example.shoppingapp.ui.feature.cart

sealed class CartIntent {
    data object LoadCart : CartIntent()
    data class RemoveItem(val productId: String) : CartIntent()
    data object PlaceOrder : CartIntent()
}