package com.app.todoapp.domain

import com.app.todoapp.data.repository.TaskRepository
import javax.inject.Inject

class GetTaskUseCase
    @Inject
    constructor(
        val taskRepository: TaskRepository,
    ) {
        operator fun invoke() = taskRepository.task
    }
