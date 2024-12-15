package com.example.shoppingapp.ui.feature.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.usecase.GetOrdersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(OrdersState())
    val state: StateFlow<OrdersState> get() = _state

    fun handleIntent(intent: OrdersIntent) {
        when (intent) {
            is OrdersIntent.LoadOrders -> loadOrders()
        }
    }

    private fun loadOrders() {
        viewModelScope.launch {
            _state.value = OrdersState(isLoading = true)
            try {
                val orders = getOrdersUseCase("currentUserId")
                _state.value = OrdersState(isLoading = false, orders = orders)
            } catch (e: Exception) {
                _state.value = OrdersState(isLoading = false, error = e.message)
            }
        }
    }
}