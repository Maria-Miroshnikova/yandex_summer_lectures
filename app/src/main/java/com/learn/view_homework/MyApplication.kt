package com.learn.view_homework

import android.app.Application
import com.learn.view_homework.repository.retrofit.AppRetrofit
import com.learn.view_homework.repository.retrofit.RepositoryRetrofit
import com.learn.view_homework.repository.room.AppRoomDatabase
import com.learn.view_homework.repository.room.RepositoryRoom

class MyApplication : Application() {
    val database by lazy { AppRoomDatabase.getAppDataBase(this) }
    val repository by lazy { RepositoryRoom(database.todoItemDao()) }
    val retrofit by lazy { AppRetrofit }
    val retrofitRepository by lazy { RepositoryRetrofit(retrofit.todoListApi)}
}