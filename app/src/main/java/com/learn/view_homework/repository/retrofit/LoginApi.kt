package com.learn.view_homework.repository.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.Body

interface LoginApi {

    suspend fun login(@Body request: LoginRequest): LoginResponse
}

@JsonClass(generateAdapter = true)
data class LoginRequest(@Json val email: String, @Json val password: String) {
}

@JsonClass(generateAdapter = true)
data class LoginResponse(@Json val token: String) {
}