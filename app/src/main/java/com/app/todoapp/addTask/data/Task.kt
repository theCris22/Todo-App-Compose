package com.app.todoapp.addTask.data

data class Task(
    val id: Long = System.currentTimeMillis(),
    val task: String,
    val isChecked: Boolean,
)
