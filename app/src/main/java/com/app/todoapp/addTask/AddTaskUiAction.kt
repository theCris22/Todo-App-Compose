package com.app.todoapp.addTask

import com.app.todoapp.addTask.data.Task

sealed interface AddTaskUiAction {
    data object OnFABClicked : AddTaskUiAction

    data object OnDismissClicked : AddTaskUiAction

    data class OnTaskWriting(
        val task: String,
    ) : AddTaskUiAction

    data class OnCheckTask(
        val task: Task,
    ) : AddTaskUiAction

    data class OnSaveTaskClicked(
        val task: String,
    ) : AddTaskUiAction
}
