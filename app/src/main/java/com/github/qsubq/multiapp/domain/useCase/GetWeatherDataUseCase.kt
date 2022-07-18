package com.github.qsubq.multiapp.domain.useCase

import com.github.qsubq.multiapp.domain.repository.WeatherRepository

class GetWeatherDataUseCase(private val repository: WeatherRepository){
    fun getWeatherData(): Any{
        // TODO: change return type
        return repository.getWeatherData()
    }
}