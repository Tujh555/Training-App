package com.bignerdranch.android.trainingapp.dagger.useCases.impl

import com.bignerdranch.android.trainingapp.dagger.repositories.ApiRepository
import com.bignerdranch.android.trainingapp.dagger.useCases.GetUserUseCase
import com.bignerdranch.android.trainingapp.data.remote.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(
    private val repository: ApiRepository
) : GetUserUseCase {
    override fun invoke(id: Int): Flow<User> = repository.getUser(id).flowOn(Dispatchers.IO)
}