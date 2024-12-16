package com.example.shoppingapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppingapp.domain.model.Order
import androidx.compose.runtime.Composable

@Composable
fun OrderItemCard(order: Order, onCancelClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Order ID: ${order.orderId}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Status: ${order.status}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Total: $${order.totalAmount}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Items: ${order.items.size}", style = MaterialTheme.typography.bodyMedium)
            if (order.status != "canceled") {
                Button(onClick = onCancelClick) {
                    Text("Cancel Order")
                }
            }
        }
    }
}