package com.learn.view_homework.dagger

import com.learn.view_homework.repository.retrofit.AppRetrofit
import com.learn.view_homework.repository.retrofit.RepositoryRetrofit
import com.learn.view_homework.repository.retrofit.TodoListApi
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

/*@Module
class MyApplicationModule @Inject constructor(val api: TodoListApi) {

    @Provides
    @Singleton
    fun provideRetrofitRepository() : RepositoryRetrofit
    {
        return RepositoryRetrofit(api)
    }
}*/