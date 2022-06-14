package com.bignerdranch.android.trainingapp.dagger.useCases

import com.bignerdranch.android.trainingapp.data.remote.User
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {

    operator fun invoke(): Flow<List<User>>
}