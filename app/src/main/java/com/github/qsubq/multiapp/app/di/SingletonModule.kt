package com.github.qsubq.multiapp.app.di

import com.github.qsubq.multiapp.data.remoteDataSource.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SingletonModule {
    private val BASE_URL = "https://api.openweathermap.org/"


    @Provides
    fun provideApiService(retrofit: retrofit2.Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(): retrofit2.Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}