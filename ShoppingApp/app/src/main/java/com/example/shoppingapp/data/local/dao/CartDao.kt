package com.example.shoppingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import com.example.shoppingapp.data.local.entity.CartEntity
import com.example.shoppingapp.data.local.entity.CartItemEntity

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: CartEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItemEntity)

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT CartItems.* FROM CartItems INNER JOIN ShoppingCart ON CartItems.cartId = ShoppingCart.cartId WHERE ShoppingCart.userId = :userId")
    suspend fun getCartItemsForUser(userId: String): List<CartItemEntity>

    @Query("DELETE FROM CartItems WHERE productId = :productId AND cartId IN (SELECT cartId FROM ShoppingCart WHERE userId = :userId)")
    suspend fun removeCartItem(userId: String, productId: String)

    @Query("DELETE FROM CartItems WHERE cartId IN (SELECT cartId FROM ShoppingCart WHERE userId = :userId)")
    suspend fun clearCart(userId: String)
}