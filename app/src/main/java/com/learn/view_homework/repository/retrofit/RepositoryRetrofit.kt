package com.learn.view_homework.repository.retrofit

import com.learn.view_homework.data_models.TodoItem
import com.learn.view_homework.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryRetrofit(private val api: TodoListApi) : Repository {

    override val todoListFlow: Flow<List<TodoItem>> = api.getTodoList()

    override suspend fun addItem(item: TodoItem) {
        api.postTodoItem(item)
    }

    override suspend fun changeItem(item: TodoItem) {
        api.putTodoItem(item.id!!, item)
    }

    override suspend fun removeItem(item: TodoItem) {
        api.deleteTodoItem(item.id!!)
    }

}