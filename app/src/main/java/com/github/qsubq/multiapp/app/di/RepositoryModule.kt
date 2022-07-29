package com.github.qsubq.multiapp.app.di

import com.github.qsubq.multiapp.data.api.ApiService
import com.github.qsubq.multiapp.data.repository.WeatherRepositoryImpl
import com.github.qsubq.multiapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun providesWeatherRepositoryImpl(apiService: ApiService): WeatherRepository {
        return WeatherRepositoryImpl(apiService)
    }
}