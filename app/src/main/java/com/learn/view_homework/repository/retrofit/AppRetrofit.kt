package com.learn.view_homework.repository.retrofit

import android.util.Log
import com.learn.view_homework.MainActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRetrofit @Inject constructor(private val client: OkHttpClient,
                                      private val BASE_URL: String,
                                      private val retrofit: Retrofit,
                                      val todoListApi: TodoListApi,
                                      val loginApi: LoginApi) {

  /*  @Inject
    lateinit var client: OkHttpClient

    @Inject
    lateinit var moshi: Moshi

    @Inject
    lateinit var BASE_URL: String

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var todoListApi: TodoListApi

    @Inject
    lateinit var loginApi: LoginApi*/

        // TODO inject

}