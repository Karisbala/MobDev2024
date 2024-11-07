package com.example.taskmanagementapp.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val creationTime: String,
    val allocatedTime: Int,
    val loggedTime: Int,
    val progress: Int,
    val workHistory: List<WorkLog> = emptyList()
)

data class WorkLog(
    val timeSpent: Int,
    val description: String
)
