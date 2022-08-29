package com.learn.view_homework.repository.retrofit

import com.learn.view_homework.data_models.TodoItem
import com.learn.view_homework.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryRetrofit @Inject constructor(private val api: TodoListApi) : Repository {

    // я так понимаю, нельзя сразу получать Flow с сервера, т. к. только мы посылаем запросы,
    // они статичны, он их обновлять не будет!
    //override val todoListFlow: Flow<List<TodoItem>> = api.getTodoList()

    // буду просить основной Repo собирать данные каждые N минут и обновлять инфу!
    suspend fun getTodoList() : List<TodoItem> = api.getTodoList()

    override suspend fun addItem(item: TodoItem) {
        api.postTodoItem(item)
    }

    override suspend fun changeItem(item: TodoItem) {
        api.putTodoItem(item.id!!, item)
    }

    override suspend fun removeItem(item: TodoItem) {
        api.deleteTodoItem(item.id!!)
    }

    suspend fun addAll(items: List<TodoItem>) {
        api.postAll(items)
    }

}