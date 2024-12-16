package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.local.dao.OrderDao
import com.example.shoppingapp.data.local.dao.ProductDao
import com.example.shoppingapp.data.local.entity.OrderEntity
import com.example.shoppingapp.data.local.entity.OrderItemEntity
import com.example.shoppingapp.data.mapper.toDomain
import com.example.shoppingapp.domain.model.CartItem
import com.example.shoppingapp.domain.model.Order
import com.example.shoppingapp.domain.repository.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val productDao: ProductDao
) : OrderRepository {
    override suspend fun placeOrder(userId: String, items: List<CartItem>): Order = withContext(Dispatchers.IO) {
        val orderId = UUID.randomUUID().toString()
        val totalAmount = items.sumOf { it.quantity * getProductPrice(it.productId) }
        val orderEntity = OrderEntity(
            orderId = orderId,
            userId = userId,
            orderDate = System.currentTimeMillis(),
            totalAmount = totalAmount,
            status = "in delivery"
        )
        orderDao.insertOrder(orderEntity)

        val orderItems = items.map {
            OrderItemEntity(
                orderItemId = UUID.randomUUID().toString(),
                orderId = orderId,
                productId = it.productId,
                quantity = it.quantity,
                price = getProductPrice(it.productId)
            )
        }
        orderDao.insertOrderItems(orderItems)

        orderEntity.toDomain(orderItems)
    }

    override suspend fun getOrders(userId: String): List<Order> = withContext(Dispatchers.IO) {
        val orderEntities = orderDao.getOrdersForUser(userId)
        orderEntities.map { order ->
            val orderItems = orderDao.getOrderItems(order.orderId)
            order.toDomain(orderItems)
        }
    }

    override suspend fun getOrderById(orderId: String): Order? = withContext(Dispatchers.IO) {
        val orderEntity = orderDao.getOrderById(orderId) ?: return@withContext null
        val orderItems = orderDao.getOrderItems(orderId)
        orderEntity.toDomain(orderItems)
    }

    override suspend fun cancelOrder(orderId: String) = withContext(Dispatchers.IO) {
        orderDao.cancelOrderById(orderId)
    }

    private suspend fun getProductPrice(productId: String): Double {
        val product = productDao.getProductById(productId)
        return product?.price ?: 0.0
    }
}