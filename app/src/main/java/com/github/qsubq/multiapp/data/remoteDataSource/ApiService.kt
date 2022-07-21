package com.github.qsubq.multiapp.data.remoteDataSource

import com.github.qsubq.multiapp.domain.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("data/2.5/weather?lat=54.18671&lon=45.18383&appid=360de6a306b1768b074c7773a2846a1f")
    suspend fun getWeatherInfo() : Response<WeatherModel>
}