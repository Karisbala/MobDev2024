package com.example.shoppingapp.data.mapper

import com.example.shoppingapp.data.local.entity.CartItemEntity
import com.example.shoppingapp.domain.model.CartItem

fun CartItemEntity.toDomain(): CartItem {
    return CartItem(
        productId = this.productId,
        quantity = this.quantity
    )
}

fun CartItem.toEntity(cartId: String, cartItemId: String): CartItemEntity {
    return CartItemEntity(
        cartItemId = cartItemId,
        cartId = cartId,
        productId = this.productId,
        quantity = this.quantity
    )
}