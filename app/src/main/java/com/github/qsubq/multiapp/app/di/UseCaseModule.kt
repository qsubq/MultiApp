package com.github.qsubq.multiapp.app.di

import com.github.qsubq.multiapp.domain.repository.WeatherRepository
import com.github.qsubq.multiapp.domain.useCase.GetWeatherDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class UseCaseModule {


    @Provides
    fun provideGetWeatherDataUseCase(repository: WeatherRepository): GetWeatherDataUseCase{
        return GetWeatherDataUseCase(repository)
    }
}