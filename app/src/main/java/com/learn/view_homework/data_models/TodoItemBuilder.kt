package com.learn.view_homework.data_models

import java.util.*

class TodoItemBuilder(var text: String, var importenceStatus: TodoItem.IMPORTANCE_STATUS,  val creationDate : Date?) {

    private var todoItem : TodoItem = TodoItem(text, importenceStatus, creationDate)

    fun setDeadline(deadline: Date?): TodoItemBuilder {
        todoItem.deadline = deadline
        return this
    }

    fun build(): TodoItem {
        return todoItem
    }

}