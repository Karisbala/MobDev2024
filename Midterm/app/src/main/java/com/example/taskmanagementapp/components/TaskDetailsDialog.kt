package com.example.taskmanagementapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.model.Task
import com.example.taskmanagementapp.viewmodel.TaskViewModel

@Composable
fun TaskDetailsDialog(task: Task, onDismiss: () -> Unit, viewModel: TaskViewModel) {
    val showLogWorkDialog by viewModel.showLogWorkDialog.collectAsState()
    val showDeleteConfirmation by viewModel.showDeleteConfirmation.collectAsState()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(task.title)
                DropdownMenuIcon(
                    onEditClick = { viewModel.editTask(task) },
                    onLogWorkClick = { viewModel.logWork(task) },
                    onDeleteClick = { viewModel.deleteTaskConfirmation() }
                )
            }
        },
        text = {
            Column {
                Text("Description: ${task.description}")
                Text("Created: ${task.creationTime}")
                Text("Allocated Time: ${task.allocatedTime} hrs")
                Text("Logged Time: ${task.loggedTime} hrs")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Work History:")
                LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                    items(task.workHistory) { log ->
                        Text("- ${log.timeSpent} hrs: ${log.description}")
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) { Text("OK") }
        }
    )

    if (showLogWorkDialog) {
        LogWorkDialog(task = task, onLog = { timeSpent, description ->
            viewModel.logWork(task.id, timeSpent, description)
            viewModel.dismissLogWorkDialog()
            viewModel.clearSelectedTask()
        },
            onDismiss = {
                viewModel.dismissLogWorkDialog()
                viewModel.clearSelectedTask()
            }
        )
    }

    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissDeleteConfirmation() },
            title = { Text("Delete Task") },
            text = { Text("Are you sure you want to delete this task?") },
            confirmButton = {
                Button(onClick = {
                    viewModel.deleteTask(task.id)
                    viewModel.dismissDeleteConfirmation()
                    onDismiss()
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                Button(onClick = { viewModel.dismissDeleteConfirmation() }) { Text("Cancel") }
            }
        )
    }
}

@Composable
fun DropdownMenuIcon(onEditClick: () -> Unit, onLogWorkClick: () -> Unit, onDeleteClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More options")
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        DropdownMenuItem(
            text = { Text("Edit Task") },
            onClick = {
            expanded = false
            onEditClick()
        })
        DropdownMenuItem(
            text = { Text("Log Work") },
            onClick = {
            expanded = false
            onLogWorkClick()
        })
        DropdownMenuItem(
            text = { Text("Delete Task") },
            onClick = {
            expanded = false
            onDeleteClick()
        })
    }
}