package com.app.todoapp.data.models

data class Task(
    val id: Int = System.currentTimeMillis().hashCode(),
    val task: String,
    val isChecked: Boolean,
)
