package com.app.todoapp.addTask

sealed interface AddTaskUiAction {
    data object OnFABClicked : AddTaskUiAction
    data object OnDismissClicked : AddTaskUiAction
    data class OnTaskWriting(val task: String) : AddTaskUiAction
    data class OnSaveTaskClicked(val task: String) : AddTaskUiAction
}
