package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.repository.OrderRepository
import javax.inject.Inject

class CancelOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(orderId: String) {
        orderRepository.cancelOrder(orderId)
    }
}