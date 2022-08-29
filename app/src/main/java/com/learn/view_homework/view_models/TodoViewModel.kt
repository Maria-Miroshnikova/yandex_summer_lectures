package com.learn.view_homework.view_models

import androidx.lifecycle.*
import com.learn.view_homework.data_models.TodoItem
import com.learn.view_homework.repository.Repository
import com.learn.view_homework.repository.room.RepositoryRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoViewModel @Inject constructor(private val repository: RepositoryRoom) : ViewModel() {

    // именно val!
    // в observer можно сразу заниматься перерасчетом количества выполненных!
    // не Mutable?
    val todoListLiveData: LiveData<List<TodoItem>> = (repository as RepositoryRoom).todoListFlow.asLiveData()

    var itemToEdit: MutableLiveData<TodoItem> = MutableLiveData()
    var itemToEditWasUpdated: Boolean = false
    // TODO: как эту систему улучшить? чтобы без флага

    fun addItem(item: TodoItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.addItem(item)
    }

    fun changeItem(item: TodoItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.changeItem(item)
    }

    fun removeItem(item: TodoItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.removeItem(item)
    }
}

/*class TodoViewModelFactory @Inject constructor(private val repository: RepositoryRoom) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/

class TodoViewModelFactory<T : ViewModel>(private val create: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return create.invoke() as T
    }
}