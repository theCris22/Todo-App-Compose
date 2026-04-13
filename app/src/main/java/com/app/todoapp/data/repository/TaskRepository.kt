package com.app.todoapp.data.repository

import com.app.todoapp.data.database.Dao.TaskDAO
import com.app.todoapp.data.database.Entity.TaskEntity
import com.app.todoapp.data.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepository
    @Inject
    constructor(
        private val taskDao: TaskDAO,
    ) {
        val task: Flow<List<Task>> = taskDao.getTasks().toTaskList()

        suspend fun inSertTask(task: Task) =
            taskDao.insertTask(
                TaskEntity(
                    id = task.id,
                    task = task.task,
                    isChecked = task.isChecked,
                ),
            )
    }

private fun Flow<List<TaskEntity>>.toTaskList(): Flow<List<Task>> =
    this.map { flow ->
        flow.map { Task(id = it.id, task = it.task, isChecked = it.isChecked) }
    }
