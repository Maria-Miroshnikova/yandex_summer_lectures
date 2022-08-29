package com.learn.view_homework.repository.retrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

// TODO: перенести обработку ошибок из интерсептора в CallAdapter клиента
class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        /// Add access token to Headers
        val token = "" // TODO
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + token)
            .build();
        var response = chain.proceed(newRequest)

        // Exceptions in responses:
        // RETRY
        val MAX_RETRY_COUNT = 4
        var retry_count = 1
        while(!response.isSuccessful && retry_count < MAX_RETRY_COUNT )
        {
            response.close()
            response = chain.proceed(newRequest);
            ++retry_count
        }

        when(response.code % 100) {
            // Client errors:
            4 -> {
                when (response.code) {
                    // Authorization
                    401 -> {
                        val newToken: String = "" // TODO
                        if (newToken != null) {
                            val newOurRequest = chain.request().newBuilder()
                                .addHeader("", newToken) //TODO
                                .build()
                            return chain.proceed(newOurRequest)
                        }
                    }
                    else -> {
                        if (newRequest.method == "GET")
                        {
                            Log.wtf("Interceptor Error", "Get request if failed")
                        }
                        else
                        {
                            Log.wtf("Interceptor Error", "Add/Update/Remove request if failed")
                        }
                    }
                }
            }
            // Server errors:
            5 -> { Log.wtf("Interceptor Error", "Server Error!") }
        }

        return response
    }
}