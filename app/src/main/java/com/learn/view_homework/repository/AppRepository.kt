package com.learn.view_homework.repository

import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.work.WorkManager
import com.learn.view_homework.data_models.TodoItem
import com.learn.view_homework.repository.retrofit.RepositoryRetrofit
import com.learn.view_homework.repository.room.RepositoryRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepository @Inject constructor (val connectivityManager: ConnectivityManager,
                                         val repositoryRoom: RepositoryRoom,
                                         val repositoryRetrofit: RepositoryRetrofit)
                                        : Repository {

    val todoListFlow: Flow<List<TodoItem>> = repositoryRoom.todoListFlow

    init {
       /* connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                val kek = coroutineScope {
                    withContext(Dispatchers.IO) {refreshLocal()}
                }
            }

            override fun onUnavailable() {
                super.onUnavailable()
            }
        })*/


    }

    override suspend fun addItem(item: TodoItem) {
        TODO("Not yet implemented")
    }

    override suspend fun changeItem(item: TodoItem) {
        TODO("Not yet implemented")
    }

    override suspend fun removeItem(item: TodoItem) {
        TODO("Not yet implemented")
    }

    suspend fun refreshLocal()
    {
        val refreshedData = repositoryRetrofit.getTodoList()
        repositoryRoom.addAll(refreshedData)
    }

    // TODO как часто?
    suspend fun refreshRemote()
    {
        repositoryRetrofit.addAll(todoListFlow.asLiveData().value!!)
    }
}