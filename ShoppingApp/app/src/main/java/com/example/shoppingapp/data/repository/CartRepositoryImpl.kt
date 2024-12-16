package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.local.dao.CartDao
import com.example.shoppingapp.data.local.entity.CartEntity
import com.example.shoppingapp.data.local.entity.CartItemEntity
import com.example.shoppingapp.data.mapper.toDomain
import com.example.shoppingapp.domain.model.CartItem
import com.example.shoppingapp.domain.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {
    override suspend fun getCartItems(userId: String): List<CartItem> = withContext(Dispatchers.IO) {
        val entities = cartDao.getCartItemsForUser(userId)
        entities.map { it.toDomain() }
    }

    override suspend fun addCartItem(userId: String, productId: String, quantity: Int) {
        withContext(Dispatchers.IO) {
            val cartId = ensureCartForUser(userId)
            val cartItemId = UUID.randomUUID().toString()
            val entity = CartItemEntity(
                cartItemId = cartItemId,
                cartId = cartId,
                productId = productId,
                quantity = quantity
            )
            cartDao.insertCartItem(entity)
        }
    }

    override suspend fun removeCartItem(userId: String, productId: String) {
        withContext(Dispatchers.IO) {
            cartDao.removeCartItem(userId, productId)
        }
    }

    override suspend fun clearCart(userId: String) {
        withContext(Dispatchers.IO) {
            cartDao.clearCart(userId)
        }
    }

    private suspend fun ensureCartForUser(userId: String): String {
        val cartId = UUID.randomUUID().toString()
        val cartEntity = CartEntity(
            cartId = cartId,
            userId = userId,
            createdAt = System.currentTimeMillis()
        )
        cartDao.insertCart(cartEntity)
        return cartId
    }

    override suspend fun updateCartItemQuantity(userId: String, productId: String, newQuantity: Int) {
        cartDao.updateCartItemQuantity(userId, productId, newQuantity)
    }
}