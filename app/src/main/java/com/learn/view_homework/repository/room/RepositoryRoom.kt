package com.learn.view_homework.repository.room

import androidx.annotation.WorkerThread
import com.learn.view_homework.data_models.TodoItem
import com.learn.view_homework.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryRoom(private val dao: TodoItemDao) : Repository {

    override val todoListFlow: Flow<List<TodoItem>> = dao.loadTodoList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun addItem(item: TodoItem) {
        dao.insertTodo(item)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun changeItem(item: TodoItem) {
        dao.updateTodo(item)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun removeItem(item: TodoItem) {
        dao.deleteTodo(item)
    }

    // synchronize with other repos??

}