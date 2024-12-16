package com.example.shoppingapp.ui.feature.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.repository.ProductRepository
import com.example.shoppingapp.domain.usecase.GetCartItemsUseCase
import com.example.shoppingapp.domain.usecase.PlaceOrderUseCase
import com.example.shoppingapp.domain.usecase.RemoveFromCartUseCase
import com.example.shoppingapp.domain.usecase.UpdateCartItemQuantityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val placeOrderUseCase: PlaceOrderUseCase,
    private val productRepository: ProductRepository,
    private val updateCartItemQuantityUseCase: UpdateCartItemQuantityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> get() = _state

    fun handleIntent(intent: CartIntent) {
        when (intent) {
            CartIntent.LoadCart -> loadCart()
            is CartIntent.RemoveItem -> removeItem(intent.productId)
            CartIntent.PlaceOrder -> placeOrder()
            is CartIntent.IncreaseQuantity -> changeQuantity(intent.productId, +1)
            is CartIntent.DecreaseQuantity -> changeQuantity(intent.productId, -1)
        }
    }

    private fun loadCart() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, orderPlaced = false)
            try {
                val cartItems = getCartItemsUseCase("currentUserId")
                val detailedItems = cartItems.map { cartItem ->
                    val product = productRepository.getProductById(cartItem.productId)
                    DetailedCartItem(
                        productId = cartItem.productId,
                        productName = product?.name ?: "Unknown",
                        price = product?.price ?: 0.0,
                        quantity = cartItem.quantity
                    )
                }
                _state.value = CartState(isLoading = false, items = detailedItems)
            } catch (e: Exception) {
                _state.value = CartState(isLoading = false, error = e.message)
            }
        }
    }

    private fun removeItem(productId: String) {
        viewModelScope.launch {
            removeFromCartUseCase("currentUserId", productId)
            loadCart()
        }
    }

    private fun placeOrder() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                placeOrderUseCase("currentUserId")
                _state.value = _state.value.copy(isLoading = false, orderPlaced = true)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    private fun changeQuantity(productId: String, delta: Int) {
        val item = _state.value.items.find { it.productId == productId } ?: return
        val newQty = (item.quantity + delta).coerceAtLeast(1)
        viewModelScope.launch {
            updateCartItemQuantityUseCase("currentUserId", productId, newQty)
            loadCart()
        }
    }
}
