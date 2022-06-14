package com.bignerdranch.android.trainingapp.dagger.components

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.trainingapp.dagger.modules.ViewModelModule
import com.bignerdranch.android.trainingapp.dagger.repositories.ApiRepository
import dagger.Component

@Component(modules = [ ViewModelModule::class ])
interface ViewModelComponent {
    fun inject(viewModel: ViewModel)

    val repository: ApiRepository
}