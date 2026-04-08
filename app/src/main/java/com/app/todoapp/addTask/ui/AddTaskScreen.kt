package com.app.todoapp.addTask.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.todoapp.R

@Preview
@Composable
private fun AddTaskScreenPreview() {
    AddTaskScreenContent(modifier = Modifier)
}

@Composable
fun AddTaskScreen(modifier: Modifier) {
    AddTaskScreenContent(modifier = modifier)
}

@Composable
private fun AddTaskScreenContent(modifier: Modifier) {
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(
                    colorResource(R.color.teal_700),
                ),
    ) {
        Text("Todo App")
    }
}
