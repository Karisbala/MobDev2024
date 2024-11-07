package com.example.taskmanagementapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.taskmanagementapp.model.Task
import com.example.taskmanagementapp.model.WorkLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    private val _showAddTaskDialog = MutableStateFlow(false)
    val showAddTaskDialog: StateFlow<Boolean> = _showAddTaskDialog

    private val _showEditTaskDialog = MutableStateFlow<Task?>(null)
    val showEditTaskDialog: StateFlow<Task?> = _showEditTaskDialog

    private val _showLogWorkDialog = MutableStateFlow(false)
    val showLogWorkDialog: StateFlow<Boolean> = _showLogWorkDialog

    private val _showDeleteConfirmation = MutableStateFlow(false)
    val showDeleteConfirmation: StateFlow<Boolean> = _showDeleteConfirmation

    private var nextId = 1

    fun addTask(title: String, description: String, creationTime: String, allocatedTime: Int) {
        val newTask = Task(
            id = nextId++,
            title = title,
            description = description,
            creationTime = creationTime,
            allocatedTime = allocatedTime,
            loggedTime = 0,
            progress = 0
        )
        _tasks.value = _tasks.value + newTask
    }

    fun updateTask(updatedTask: Task) {
        _tasks.value = _tasks.value.map { if (it.id == updatedTask.id) updatedTask else it }
    }

    fun deleteTask(taskId: Int) {
        _tasks.value = _tasks.value.filter { it.id != taskId }
    }

    fun logWork(taskId: Int, timeSpent: Int, description: String) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == taskId) {
                val newLog = WorkLog(timeSpent = timeSpent, description = description)
                val updatedWorkHistory = task.workHistory + newLog
                val updatedLoggedTime = task.loggedTime + timeSpent
                val updatedProgress = (updatedLoggedTime * 100) / task.allocatedTime
                task.copy(
                    loggedTime = updatedLoggedTime,
                    progress = updatedProgress,
                    workHistory = updatedWorkHistory
                )
            } else {
                task
            }
        }
    }

    fun selectTask(task: Task) {
        _selectedTask.value = task
    }

    fun clearSelectedTask() {
        _selectedTask.value = null
    }

    fun addNewTask() {
        _showAddTaskDialog.value = true
    }

    fun dismissAddTaskDialog() {
        _showAddTaskDialog.value = false
    }

    fun editTask(task: Task) {
        _showEditTaskDialog.value = task
    }

    fun dismissEditTaskDialog() {
        _showEditTaskDialog.value = null
    }

    fun logWork(task: Task) {
        _selectedTask.value = task
        _showLogWorkDialog.value = true
    }

    fun dismissLogWorkDialog() {
        _showLogWorkDialog.value = false
    }

    fun deleteTaskConfirmation() {
        _showDeleteConfirmation.value = true
    }

    fun dismissDeleteConfirmation() {
        _showDeleteConfirmation.value = false
    }
}
