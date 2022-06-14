package com.bignerdranch.android.trainingapp.dagger.useCases

import com.bignerdranch.android.trainingapp.data.remote.User
import kotlinx.coroutines.flow.Flow

interface GetUserUseCase {

    operator fun invoke(id: Int): Flow<User>
}