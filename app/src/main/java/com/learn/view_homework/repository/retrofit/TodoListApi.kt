package com.learn.view_homework.repository.retrofit

import com.learn.view_homework.data_models.TodoItem
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface TodoListApi {
    /**
     * Returns a [List] of [TodoItem] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "TODO" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("list")
    fun getTodoList(): Flow<List<TodoItem>>

    @GET("list/{id}")
    suspend fun getTodoItem(@Path("id") id: Int) : TodoItem

    @POST("list")
    suspend fun postTodoItem(@Body item: TodoItem)

    // TODO
    @PUT("list/{id}")
    suspend fun putTodoItem(@Path("id") id: Int, @Body item: TodoItem)

    @DELETE("list/{id}")
    suspend fun deleteTodoItem(@Path("id") id: Int)

    /* обновление списка на сервере еще!*/
}