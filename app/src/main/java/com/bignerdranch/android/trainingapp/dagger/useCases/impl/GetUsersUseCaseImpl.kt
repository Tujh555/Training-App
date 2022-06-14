package com.bignerdranch.android.trainingapp.dagger.useCases.impl

import com.bignerdranch.android.trainingapp.dagger.repositories.ApiRepository
import com.bignerdranch.android.trainingapp.dagger.useCases.GetUsersUseCase
import com.bignerdranch.android.trainingapp.data.remote.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUsersUseCaseImpl @Inject constructor(
    private val repository: ApiRepository
) : GetUsersUseCase {
    override fun invoke(): Flow<List<User>> = repository.getUsers().flowOn(Dispatchers.IO)
}