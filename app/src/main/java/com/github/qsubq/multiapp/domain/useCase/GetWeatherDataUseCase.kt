package com.github.qsubq.multiapp.domain.useCase

import com.github.qsubq.multiapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(private val repository: WeatherRepository){
    suspend operator fun invoke(city:String) = repository.getWeatherData(city)
}