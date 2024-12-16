package com.example.shoppingapp.domain.repository

import com.example.shoppingapp.domain.model.CartItem

interface CartRepository {
    suspend fun getCartItems(userId: String): List<CartItem>
    suspend fun addCartItem(userId: String, productId: String, quantity: Int)
    suspend fun removeCartItem(userId: String, productId: String)
    suspend fun clearCart(userId: String)
    suspend fun updateCartItemQuantity(userId: String, productId: String, newQuantity: Int)
}