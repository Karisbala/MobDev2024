package com.example.shoppingapp.ui.feature.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.usecase.GetCartItemsUseCase
import com.example.shoppingapp.domain.usecase.PlaceOrderUseCase
import com.example.shoppingapp.domain.usecase.RemoveFromCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val placeOrderUseCase: PlaceOrderUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> get() = _state

    fun handleIntent(intent: CartIntent) {
        when (intent) {
            is CartIntent.LoadCart -> loadCart()
            is CartIntent.RemoveItem -> removeItem(intent.productId)
            is CartIntent.PlaceOrder -> placeOrder()
        }
    }

    private fun loadCart() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, orderPlaced = false)
            try {
                val items = getCartItemsUseCase("currentUserId")
                _state.value = CartState(isLoading = false, cartItems = items)
            } catch (e: Exception) {
                _state.value = CartState(isLoading = false, error = e.message)
            }
        }
    }

    private fun removeItem(productId: String) {
        viewModelScope.launch {
            try {
                removeFromCartUseCase("currentUserId", productId)
                loadCart()
            } catch (e: Exception) {
                // handle error if needed
            }
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
}