package com.bignerdranch.android.trainingapp.dagger.modules

import com.bignerdranch.android.trainingapp.dagger.useCases.GetUserUseCase
import com.bignerdranch.android.trainingapp.dagger.useCases.GetUsersUseCase
import com.bignerdranch.android.trainingapp.dagger.useCases.impl.GetUserUseCaseImpl
import com.bignerdranch.android.trainingapp.dagger.useCases.impl.GetUsersUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCasesBinding {

    @Binds
    fun bindGetUsersUseCase(getUsersUseCaseImpl: GetUsersUseCaseImpl): GetUsersUseCase

    @Binds
    fun bindGetUserUseCase(getUserUseCaseImpl: GetUserUseCaseImpl): GetUserUseCase
}