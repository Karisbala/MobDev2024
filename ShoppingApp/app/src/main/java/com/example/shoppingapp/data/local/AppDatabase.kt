package com.example.shoppingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppingapp.data.local.dao.*
import com.example.shoppingapp.data.local.entity.*

@Database(
    entities = [
        UserEntity::class,
        ProductEntity::class,
        CartEntity::class,
        CartItemEntity::class,
        OrderEntity::class,
        OrderItemEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao
}