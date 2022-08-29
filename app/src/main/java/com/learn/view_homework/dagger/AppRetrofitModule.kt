package com.learn.view_homework.dagger

import com.learn.view_homework.repository.retrofit.LoginApi
import com.learn.view_homework.repository.retrofit.MyInterceptor
import com.learn.view_homework.repository.retrofit.TodoListApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class AppRetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(MyInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi() : Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideBaseUrl() : String {
        return "https://android-kotlin-fun-mars-server.appspot.com"
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .client(provideOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .baseUrl(provideBaseUrl())
            .build()
    }

    @Provides
    @Singleton
    fun provideTodoListApi() : TodoListApi {
        return provideRetrofit().create(TodoListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginApi() : LoginApi {
        return provideRetrofit().create(LoginApi::class.java)
    }
}