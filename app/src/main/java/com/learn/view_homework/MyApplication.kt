package com.learn.view_homework

import android.app.Application
import com.learn.view_homework.dagger.DaggerMyApplicationComponent
import com.learn.view_homework.dagger.MyApplicationComponent
import com.learn.view_homework.repository.retrofit.AppRetrofit
import com.learn.view_homework.repository.retrofit.RepositoryRetrofit
import com.learn.view_homework.repository.room.AppRoomDatabase
import com.learn.view_homework.repository.room.RepositoryRoom
import com.learn.view_homework.view_models.TodoViewModel
import javax.inject.Inject

class MyApplication: Application() {
    //val database by lazy { AppRoomDatabase.getAppDataBase(this) }
    //val repository by lazy { RepositoryRoom(database.todoItemDao()) }
    //val retrofit by lazy { AppRetrofit }
    //val retrofitRepository by lazy { RepositoryRetrofit(retrofit.todoListApi)}

    val myApplicationComponent : MyApplicationComponent by lazy {
        DaggerMyApplicationComponent.factory().create(this)
    }
    // TODO inject
}