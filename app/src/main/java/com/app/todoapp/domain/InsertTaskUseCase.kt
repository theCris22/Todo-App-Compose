package com.app.todoapp.domain

import com.app.todoapp.data.models.Task
import com.app.todoapp.data.repository.TaskRepository
import javax.inject.Inject

class
InsertTaskUseCase
    @Inject
    constructor(
        val taskRepository: TaskRepository,
    ) {
        suspend operator fun invoke(task: Task) = taskRepository.inSertTask(task)
    }
