package com.github.qsubq.multiapp.data.repository

import com.github.qsubq.multiapp.data.api.ApiService
import com.github.qsubq.multiapp.domain.model.WeatherModel
import com.github.qsubq.multiapp.domain.repository.WeatherRepository
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: ApiService) : WeatherRepository {
    override suspend fun getWeatherData(city: String): NetworkResult<WeatherModel> {
        return handleApi { api.getWeatherInfo(city) }
    }


    private suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>,
    ): NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                NetworkResult.Success(body)
            } else {
                NetworkResult.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            NetworkResult.Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }


    sealed class NetworkResult<T : Any> {
        class Loading<T : Any> : NetworkResult<T>()
        class Success<T : Any>(val data: T) : NetworkResult<T>()
        class Error<T : Any>(val code: Int, val message: String?) : NetworkResult<T>()
        class Exception<T : Any>(val e: Throwable) : NetworkResult<T>()
    }

}