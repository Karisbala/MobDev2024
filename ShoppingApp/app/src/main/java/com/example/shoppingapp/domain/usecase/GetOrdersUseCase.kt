package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.model.Order
import com.example.shoppingapp.domain.repository.OrderRepository
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(userId: String): List<Order> {
        return orderRepository.getOrders(userId)
    }
}