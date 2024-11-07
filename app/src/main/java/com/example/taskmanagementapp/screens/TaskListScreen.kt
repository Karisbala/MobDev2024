@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.taskmanagementapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.components.TaskCard
import com.example.taskmanagementapp.components.TaskDetailsDialog
import com.example.taskmanagementapp.components.TaskInputDialog
import com.example.taskmanagementapp.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()
    val showAddTaskDialog by viewModel.showAddTaskDialog.collectAsState()
    val showEditTaskDialog by viewModel.showEditTaskDialog.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Task Management") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(tasks) { task ->
                    TaskCard(
                        task,
                        onClick = { viewModel.selectTask(task) },
                        onEditClick = { viewModel.editTask(task) },
                        onLogWorkClick = { viewModel.logWork(task) }
                    )
                }
            }
            FloatingActionButton(
                onClick = { viewModel.addNewTask() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text("+")
            }
        }
    }

    selectedTask?.let {
        TaskDetailsDialog(task = it, onDismiss = { viewModel.clearSelectedTask() }, viewModel = viewModel)
    }

    if (showAddTaskDialog) {
        TaskInputDialog(
            title = "Add New Task",
            onConfirm = { title, description, allocatedTime ->
                val currentTime = SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()
                ).format(Date())
                viewModel.addTask(title, description, currentTime, allocatedTime)
                viewModel.dismissAddTaskDialog()
            },
            onDismiss = { viewModel.dismissAddTaskDialog() }
        )
    }

    showEditTaskDialog?.let { task ->
        TaskInputDialog(
            title = "Edit Task",
            initialTitle = task.title,
            initialDescription = task.description,
            initialAllocatedTime = task.allocatedTime.toString(),
            onConfirm = { updatedTitle, updatedDescription, updatedAllocatedTime ->
                val updatedTask = task.copy(
                    title = updatedTitle,
                    description = updatedDescription,
                    allocatedTime = updatedAllocatedTime
                )
                viewModel.updateTask(updatedTask)
                viewModel.dismissEditTaskDialog()
                viewModel.clearSelectedTask()
            },
            onDismiss = { viewModel.dismissEditTaskDialog() }
        )
    }
}
