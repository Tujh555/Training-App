package com.bignerdranch.android.trainingapp.dagger.modules

import com.bignerdranch.android.trainingapp.dagger.repositories.ApiRepository
import com.bignerdranch.android.trainingapp.dagger.repositories.impl.ApiRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindApiRepository(apiRepositoryImpl: ApiRepositoryImpl): ApiRepository
}