package com.app.todoapp.addTask.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.todoapp.addTask.AddTaskUiAction
import com.app.todoapp.addTask.AddTaskUiState
import com.app.todoapp.addTask.data.Task
import com.app.todoapp.addTask.ui.components.AddTaskBottomSheet

@Preview(device = "id:small_phone")
@Composable
private fun AddTaskScreenPreview() {
    AddTaskScreenContent(
        modifier = Modifier,
        uiState =
            AddTaskUiState(
                taskList =
                    listOf(
                        Task(
                            task = "This are my notes",
                            isChecked = false,
                        ),
                    ),
            ),
        uiAction = {},
    )
}

@Composable
fun AddTaskScreen(
    modifier: Modifier,
    viewModel: AddTaskViewModel = hiltViewModel(),
) {
    val addTaskUiState by viewModel.addTaskUiState.collectAsStateWithLifecycle()

    AddTaskScreenContent(
        modifier = modifier,
        uiState = addTaskUiState,
        uiAction = { viewModel.addTaskUiAction(it) },
    )

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
    uiState: AddTaskUiState,
    uiAction: uiAction,
) {
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.White),
    ) {
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize(),
        ) {
            items(
                items = uiState.taskList,
                key = { it.id },
            ) { task ->
                TaskItem(task = task, uiAction = uiAction)
            }
        }

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

@Composable
fun TaskItem(
    task: Task,
    uiAction: uiAction,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                .clickable(onClick = {
                }),
        shape = MaterialTheme.shapes.extraSmall,
        colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = task.task,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium,
            )
            Checkbox(
                checked = task.isChecked,
                onCheckedChange = {
                    uiAction(AddTaskUiAction.OnCheckTask(task))
                },
            )
        }
    }
}

typealias uiAction = (AddTaskUiAction) -> Unit
