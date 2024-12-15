package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.model.Order
import com.example.shoppingapp.domain.repository.CartRepository
import com.example.shoppingapp.domain.repository.OrderRepository
import javax.inject.Inject

class PlaceOrderUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(userId: String): Order {
        val cartItems = cartRepository.getCartItems(userId)
        val order = orderRepository.placeOrder(userId, cartItems)
        cartRepository.clearCart(userId)
        return order
    }
}