package com.github.qsubq.multiapp.domain.useCase

import com.github.qsubq.multiapp.data.repository.WeatherRepositoryImpl
import com.github.qsubq.multiapp.domain.model.*
import com.github.qsubq.multiapp.domain.repository.WeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetWeatherDataUseCaseTest {
    private val repository = mock<WeatherRepository>()

    @AfterEach
    fun afterEach() {
        Mockito.reset(repository)
    }

    @Test
    fun `should return success response`() {
        runBlocking {
            val useCase = GetWeatherDataUseCase(repository)
            val testCityName = "Saransk"
            val testResponse = WeatherRepositoryImpl.NetworkResult.Success(WeatherModel("",
                Clouds(0),
                0,
                Coord(0.0, 0.0),
                1,
                2,
                Main(5.4, 6, 6, 6, 0, 3.0, 5.0, 5.0),
                testCityName,
                Rain(8.0), Sys("Russia", 0, 1), 10, 15, listOf(), Wind(6, 5.0, 6.0)))

            Mockito.`when`(useCase.invoke(testCityName)).thenReturn(testResponse)

            val actual = useCase.invoke(testCityName)

            Assertions.assertEquals(testResponse, actual)
        }
    }
}