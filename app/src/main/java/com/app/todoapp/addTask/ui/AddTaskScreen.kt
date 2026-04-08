package com.app.todoapp.addTask.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.todoapp.addTask.AddTaskUiAction
import com.app.todoapp.addTask.ui.components.AddTaskBottomSheet

@Preview
@Composable
private fun AddTaskScreenPreview() {
    AddTaskScreenContent(modifier = Modifier, uiAction = {})
}

@Composable
fun AddTaskScreen(
    modifier: Modifier,
    viewModel: AddTaskViewModel = hiltViewModel(),
) {
    val addTaskUiState by viewModel.addTaskUiState.collectAsStateWithLifecycle()

    AddTaskScreenContent(modifier = modifier, uiAction = { viewModel.addTaskUiAction(it) })

    if (addTaskUiState.showAddTaskBottomSheet) {
        AddTaskBottomSheet(
            uiState = addTaskUiState,
            uiAction = {
                viewModel.addTaskUiAction(it)
            },
        )
    }
}

@Composable
private fun AddTaskScreenContent(
    modifier: Modifier,
    uiAction: uiAction,
) {
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.White),
    ) {
        FabAddTask(
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
            uiAction,
        )
    }
}

@Composable
fun FabAddTask(
    modifier: Modifier = Modifier,
    uiAction: uiAction,
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = { uiAction(AddTaskUiAction.OnFABClicked) },
        content = {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        },
    )
}

typealias uiAction = (AddTaskUiAction) -> Unit
