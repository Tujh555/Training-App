package com.bignerdranch.android.trainingapp.retrofit

import com.bignerdranch.android.trainingapp.data.remote.User
import retrofit2.http.GET
import retrofit2.http.Path

interface MockApi {

    @GET("/Users")
    suspend fun getAllUsers(): List<User>

    @GET("/Users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User
}