package com.github.qsubq.multiapp.data.api

import com.github.qsubq.multiapp.domain.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("data/2.5/weather?q=saransk&units=metric&appid=$apiKey")
    suspend fun getWeatherInfo(): Response<WeatherModel>

    companion object {
        private const val apiKey = "360de6a306b1768b074c7773a2846a1f"
    }
}