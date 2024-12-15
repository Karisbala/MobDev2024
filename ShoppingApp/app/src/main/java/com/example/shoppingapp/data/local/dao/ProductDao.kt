package com.example.shoppingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppingapp.data.local.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM Products")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM Products WHERE productId = :productId LIMIT 1")
    suspend fun getProductById(productId: String): ProductEntity?
}