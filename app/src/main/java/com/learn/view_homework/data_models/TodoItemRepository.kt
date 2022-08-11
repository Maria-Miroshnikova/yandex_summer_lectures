package com.learn.view_homework.data_models

import java.util.*
import kotlin.collections.ArrayList

public class TodoItemRepository(private var todoList : ArrayList<TodoItem> = ArrayList()): List<TodoItem> by todoList {

    fun addTodoItem(todoItem: TodoItem)
    {
        todoItem.id = UUID.randomUUID().toString().substring(0, 10)
        todoList.add(todoItem)
    }

    fun getTodoList(): ArrayList<TodoItem> {
        return todoList
    }


}