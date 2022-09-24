package com.github.qsubq.multiapp.domain.repository

import com.github.qsubq.multiapp.data.repository.WeatherRepositoryImpl
import com.github.qsubq.multiapp.data.repository.WeatherRepositoryImpl.*
import com.github.qsubq.multiapp.domain.model.WeatherModel

interface WeatherRepository {
    suspend fun getWeatherData(city: String): NetworkResult<WeatherModel>
}

