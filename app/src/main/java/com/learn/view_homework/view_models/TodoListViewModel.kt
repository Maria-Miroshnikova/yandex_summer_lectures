package com.learn.view_homework.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.view_homework.data_models.TodoItem
import com.learn.view_homework.data_models.TodoItemRepository
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class TodoListViewModel: ViewModel() {
    var repository: TodoItemRepository = TodoItemRepository()

    var itemToEdit: MutableLiveData<Int> = MutableLiveData()
    var itemToEditWasUpdated: Boolean = false
    var countDoneItems: MutableLiveData<Int> = MutableLiveData()

    init {
        val test_todo = TodoItem("Deprecated Gradle features were used in this build, making it incompatible with Gradle 8.0.\n" +
                "Use '--warning-mode all' to show the individual deprecation warnings.", TodoItem.IMPORTANCE_STATUS.URGENTLY, Calendar.getInstance().time)
        repository.addTodoItem(test_todo)
        countDoneItems.value = 0
    }

    fun setItemToEdit(value: Int)
    {
        itemToEdit.value = value

    }

    fun addItem(item: TodoItem)
    {
        repository.addTodoItem(item)
        if (item.isDone)
            countDoneItems.value = countDoneItems.value?.plus(1)
    }

    fun changeItem(idx: Int, item: TodoItem)
    {
        if (repository.getTodoList()[idx].isDone && !item.isDone)
            countDoneItems.value = countDoneItems.value?.minus(1)
        else if(!repository.getTodoList()[idx].isDone && item.isDone)
            countDoneItems.value = countDoneItems.value?.plus(1)
        repository.getTodoList()[idx] = item
    }

    fun removeItem(idx: Int)
    {
        if (repository.getTodoList()[idx].isDone)
            countDoneItems.value = countDoneItems.value?.minus(1)
        repository.getTodoList().removeAt(idx)
    }

    fun checkItem(idx: Int)
    {
        when(repository.getTodoList()[idx].isDone)
        {
            true -> countDoneItems.value = countDoneItems.value?.minus(1)
            false -> countDoneItems.value = countDoneItems.value?.plus(1)
        }
        repository.getTodoList()[idx].isDone = !repository.getTodoList()[idx].isDone
    }
}