package com.learn.view_homework.repository.room

import androidx.room.*
import com.learn.view_homework.data_models.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(item: TodoItem)

    @Update
    suspend fun updateTodo(item: TodoItem)

    @Delete
    suspend fun deleteTodo(item: TodoItem)

    @Query("SELECT * FROM todoitem WHERE id = :id")
    suspend fun loadTodoById(id: Int): TodoItem

    @Query("SELECT * from todoitem")
    fun loadTodoList(): Flow<List<TodoItem>>
}