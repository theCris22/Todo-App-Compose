package com.app.todoapp.addTask.ui

import androidx.lifecycle.ViewModel
import com.app.todoapp.addTask.AddTaskUiAction
import com.app.todoapp.addTask.AddTaskUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel
    @Inject
    constructor() : ViewModel() {
        private val _addTaskUiState = MutableStateFlow(AddTaskUiState())
        val addTaskUiState = _addTaskUiState.asStateFlow()

        fun addTaskUiAction(addTaskUiAction: AddTaskUiAction) {
            when (addTaskUiAction) {
                AddTaskUiAction.OnFABClicked -> {
                    showAddTaskBottomSheet(true)
                }

                AddTaskUiAction.OnDismissClicked -> {
                    showAddTaskBottomSheet(false)
                }

                is AddTaskUiAction.OnTaskWriting -> {
                    onNoteWriting(addTaskUiAction.task)
                }

                is AddTaskUiAction.OnSaveTaskClicked -> {}
            }
        }

        private fun showAddTaskBottomSheet(show: Boolean) = _addTaskUiState.update { it.copy(showAddTaskBottomSheet = show) }

        private fun onNoteWriting(task: String) = _addTaskUiState.update { it.copy(task = task) }
    }
