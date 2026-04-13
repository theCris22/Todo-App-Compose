package com.app.todoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.todoapp.data.database.Dao.TaskDAO
import com.app.todoapp.data.database.Entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TodoDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO
}
