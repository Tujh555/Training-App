package com.bignerdranch.android.trainingapp.dagger.modules

import dagger.Module

@Module(includes = [ NetworkModule::class, RepositoryModule::class] )
class ViewModelModule