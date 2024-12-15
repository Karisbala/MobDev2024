package com.example.shoppingapp.data.mapper

import com.example.shoppingapp.data.local.entity.OrderEntity
import com.example.shoppingapp.data.local.entity.OrderItemEntity
import com.example.shoppingapp.domain.model.Order
import com.example.shoppingapp.domain.model.OrderItem

fun OrderEntity.toDomain(items: List<OrderItemEntity>): Order {
    return Order(
        orderId = this.orderId,
        userId = this.userId,
        orderDate = this.orderDate,
        totalAmount = this.totalAmount,
        status = this.status,
        items = items.map { it.toDomain() }
    )
}

fun OrderItemEntity.toDomain(): OrderItem {
    return OrderItem(
        productId = this.productId,
        quantity = this.quantity,
        price = this.price
    )
}