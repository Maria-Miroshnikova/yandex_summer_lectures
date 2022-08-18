package com.learn.view_homework.repository

import com.learn.view_homework.data_models.TodoItem
import kotlinx.coroutines.flow.Flow

interface Repository {

    val todoListFlow: Flow<List<TodoItem>>

    suspend fun addItem(item: TodoItem)

    suspend fun changeItem(item: TodoItem)

    suspend fun removeItem(item: TodoItem)
}