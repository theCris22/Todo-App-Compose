package com.app.todoapp.addTask

data class AddTaskUiState(
    val emptyState: Boolean = true,
    val task: String = "",
    val showAddTaskBottomSheet: Boolean = false,
)
