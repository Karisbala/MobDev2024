package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.model.CartItem
import com.example.shoppingapp.domain.repository.CartRepository
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(userId: String): List<CartItem> {
        return cartRepository.getCartItems(userId)
    }
}