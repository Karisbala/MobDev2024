package com.example.taskmanagementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.taskmanagementapp.screens.TaskListScreen
import com.example.taskmanagementapp.ui.theme.TaskManagementAppTheme
import com.example.taskmanagementapp.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagementAppTheme {
                val taskViewModel: TaskViewModel = viewModels<TaskViewModel>().value
                TaskListScreen(taskViewModel)
            }
        }
    }
}

