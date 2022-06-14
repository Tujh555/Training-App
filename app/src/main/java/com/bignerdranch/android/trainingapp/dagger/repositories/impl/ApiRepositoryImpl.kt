package com.bignerdranch.android.trainingapp.dagger.repositories.impl

import com.bignerdranch.android.trainingapp.dagger.repositories.ApiRepository
import com.bignerdranch.android.trainingapp.retrofit.MockApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val mockApi: MockApi
) : ApiRepository {
    override fun getUsers() = flow {
        emit(mockApi.getAllUsers())
    }

    override fun getUser(id: Int) = flow {
        emit(mockApi.getUserById(id))
    }
}