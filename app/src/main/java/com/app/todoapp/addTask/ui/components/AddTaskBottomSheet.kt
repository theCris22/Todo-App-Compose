package com.app.todoapp.addTask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.todoapp.addTask.AddTaskUiAction
import com.app.todoapp.addTask.AddTaskUiState
import com.app.todoapp.addTask.ui.uiAction
import kotlinx.coroutines.launch

@Preview(device = "id:small_phone")
@Composable
fun AddTaskBottomSheetPreview() {
    AddTaskBottomSheetContent(uiState = AddTaskUiState(task = "This are my notes"), uiAction = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskBottomSheet(
    uiState: AddTaskUiState,
    uiAction: uiAction,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = Modifier,
        content = { AddTaskBottomSheetContent(uiState = uiState, uiAction = uiAction) },
        dragHandle = {
            Box(
                Modifier
                    .padding(vertical = 20.dp)
                    .width(32.dp)
                    .height(4.dp)
                    .background(Color.Gray, RoundedCornerShape(2.dp)),
            )
        },
        sheetState = sheetState,
        onDismissRequest = {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) uiAction(AddTaskUiAction.OnDismissClicked)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskBottomSheetContent(
    uiState: AddTaskUiState,
    uiAction: uiAction,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(Color.White),
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Header()
        Spacer(modifier = Modifier.height(32.dp))
        TaskField(uiAction = uiAction, uiState = uiState)
        Spacer(modifier = Modifier.height(16.dp))
        SaveButton(uiState = uiState, uiAction = uiAction)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun Header() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = "Add task",
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    )
}

@Composable
fun TaskField(
    uiState: AddTaskUiState,
    uiAction: uiAction,
) {
    OutlinedTextField(
        value = uiState.task,
        onValueChange = { task -> uiAction(AddTaskUiAction.OnTaskWriting(task)) },
        modifier =
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 16.dp),
        label = {
            Text(
                modifier = Modifier,
                text = "Write here your task...",
                fontSize = 12.sp,
                fontStyle = FontStyle.Italic,
            )
        },
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
    )
}

@Composable
fun SaveButton(
    uiState: AddTaskUiState,
    uiAction: uiAction,
) {
    Button(
        onClick = {
            uiAction(AddTaskUiAction.OnSaveTaskClicked(uiState.task))
        },
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        content = { Text("Save") },
    )
}
