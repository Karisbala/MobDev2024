package com.example.taskmanagementapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import com.example.taskmanagementapp.model.Task

@Composable
fun LogWorkDialog(task: Task, onLog: (Int, String) -> Unit, onDismiss: () -> Unit) {
    var timeSpent by remember { mutableStateOf("0") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Log Work for ${task.title}") },
        text = {
            Column {
                OutlinedTextField(
                    value = timeSpent,
                    onValueChange = { timeSpent = it },
                    label = { Text("Time Spent (hrs)") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Work Description") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onLog(timeSpent.toIntOrNull() ?: 0, description)
            }) {
                Text("Log")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancel") }
        }
    )
}