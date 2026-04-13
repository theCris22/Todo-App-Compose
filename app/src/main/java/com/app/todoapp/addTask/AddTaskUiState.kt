package com.app.todoapp.addTask

import com.app.todoapp.data.models.Task

data class AddTaskUiState(
    val emptyState: Boolean = true,
    val newTask: String = "",
    val taskList: List<Task> = emptyList(),
    val showAddTaskBottomSheet: Boolean = false,
) {
    val enableSaveButton: Boolean = newTask.isNotEmpty()
}
