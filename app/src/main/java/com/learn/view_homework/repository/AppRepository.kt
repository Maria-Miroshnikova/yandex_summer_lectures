package com.learn.view_homework.repository

import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.work.WorkManager
import com.learn.view_homework.data_models.TodoItem
import com.learn.view_homework.repository.retrofit.RepositoryRetrofit
import com.learn.view_homework.repository.room.RepositoryRoom
import kotlinx.coroutines.flow.Flow

class AppRepository(val connectivityManager: ConnectivityManager,
                    val repositoryRoom: RepositoryRoom,
                    val repositoryRetrofit: RepositoryRetrofit) : Repository {

    override val todoListFlow: Flow<List<TodoItem>> = TODO()

    init {
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
            }

            override fun onUnavailable() {
                super.onUnavailable()
            }
        })


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

    fun updateDB()
    {
        TODO()
    }
}