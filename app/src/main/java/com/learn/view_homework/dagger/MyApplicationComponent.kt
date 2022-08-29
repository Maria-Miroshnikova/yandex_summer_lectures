package com.learn.view_homework.dagger

import android.content.Context
import com.learn.view_homework.MainActivity
import com.learn.view_homework.MyApplication
import com.learn.view_homework.repository.retrofit.AppRetrofit
import com.learn.view_homework.repository.retrofit.RepositoryRetrofit
import com.learn.view_homework.view_models.TodoViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component (modules = [AppRetrofitModule::class, AppRoomDatabaseModule::class])//, MyApplicationModule::class])
@Singleton
interface MyApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): MyApplicationComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(myApplication: MyApplication)
    fun inject(appRetrofit: AppRetrofit)
    fun inject(todoViewModel: TodoViewModel)

    fun todoViewModel() : TodoViewModel

}