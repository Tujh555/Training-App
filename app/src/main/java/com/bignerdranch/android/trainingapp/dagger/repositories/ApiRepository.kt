package com.bignerdranch.android.trainingapp.dagger.repositories

import com.bignerdranch.android.trainingapp.data.remote.User
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    fun getUsers(): Flow<List<User>>

    fun getUser(id: Int): Flow<User>
}