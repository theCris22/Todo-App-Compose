package com.app.todoapp.di

import android.content.Context
import androidx.room.Room
import com.app.todoapp.data.database.TodoDataBase
import com.app.todoapp.utils.DATA_BASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideTaskDao(todoDataBase: TodoDataBase) = todoDataBase.taskDao()

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context,
    ): TodoDataBase = Room.databaseBuilder(appContext, TodoDataBase::class.java, DATA_BASE_NAME).build()
}
