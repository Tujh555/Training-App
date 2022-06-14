package com.bignerdranch.android.trainingapp.dagger.modules

import com.bignerdranch.android.trainingapp.dagger.useCases.GetUsersUseCase
import com.bignerdranch.android.trainingapp.dagger.useCases.impl.GetUsersUseCaseImpl
import com.bignerdranch.android.trainingapp.retrofit.MockApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [ UseCasesBinding::class ])
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().build()

    @Provides
    fun provideMockApi(httpClient: OkHttpClient): MockApi = Retrofit.Builder()
        .baseUrl("https://62a4e98447e6e4006399dfe7.mockapi.io")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
        .create(MockApi::class.java)
}