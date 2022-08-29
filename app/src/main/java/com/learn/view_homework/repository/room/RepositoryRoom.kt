package com.learn.view_homework.repository.room

import androidx.annotation.WorkerThread
import com.learn.view_homework.data_models.TodoItem
import com.learn.view_homework.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryRoom @Inject constructor(private val dao: TodoItemDao) : Repository {

    val todoListFlow: Flow<List<TodoItem>> = dao.loadTodoList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun addItem(item: TodoItem) {
        dao.insertTodo(item)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addAll(items: List<TodoItem>) {
        dao.insertAll(items)
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