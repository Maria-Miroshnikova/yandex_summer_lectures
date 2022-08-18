package com.learn.view_homework.repository.retrofit

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object AppRetrofit {

    val client: OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(MyInterceptor())
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val BASE_URL = ""

    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

   val todoListApi : TodoListApi by lazy { retrofit.create(TodoListApi::class.java) }

    val loginApi : LoginApi by lazy { retrofit.create(LoginApi::class.java)}

}