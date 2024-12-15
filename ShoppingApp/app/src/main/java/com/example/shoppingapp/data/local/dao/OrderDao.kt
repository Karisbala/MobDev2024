package com.example.shoppingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppingapp.data.local.entity.OrderEntity
import com.example.shoppingapp.data.local.entity.OrderItemEntity

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderItems(items: List<OrderItemEntity>)

    @Query("SELECT * FROM Orders WHERE userId = :userId")
    suspend fun getOrdersForUser(userId: String): List<OrderEntity>

    @Query("SELECT * FROM OrderItems WHERE orderId = :orderId")
    suspend fun getOrderItems(orderId: String): List<OrderItemEntity>

    @Query("SELECT * FROM Orders WHERE orderId = :orderId LIMIT 1")
    suspend fun getOrderById(orderId: String): OrderEntity?
}