package com.app.todoapp.addTask.ui

import androidx.lifecycle.ViewModel
import com.app.todoapp.addTask.AddTaskUiAction
import com.app.todoapp.addTask.AddTaskUiState
import com.app.todoapp.addTask.data.Task
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

                is AddTaskUiAction.OnCheckTask -> {
                    onCheckTask(addTaskUiAction.task)
                }

                is AddTaskUiAction.OnSaveTaskClicked -> {
                    onSaveTaskClicked()
                    onNoteWriting("")
                    showAddTaskBottomSheet(false)
                }
            }
        }

        private fun showAddTaskBottomSheet(show: Boolean) = _addTaskUiState.update { it.copy(showAddTaskBottomSheet = show) }

        private fun onNoteWriting(task: String) = _addTaskUiState.update { it.copy(newTask = task) }

        private fun onCheckTask(task: Task) {
            _addTaskUiState.update { currentState ->
                val updatedList =
                    currentState.taskList.map {
                        if (it.id == task.id) {
                            it.copy(isChecked = !it.isChecked)
                        } else {
                            it
                        }
                    }
                currentState.copy(taskList = updatedList)
            }
        }

        private fun onSaveTaskClicked() =
            _addTaskUiState.update { it.copy(taskList = it.taskList.plus(Task(task = it.newTask, isChecked = false))) }
    }
