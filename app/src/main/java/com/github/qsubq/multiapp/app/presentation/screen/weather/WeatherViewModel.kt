package com.github.qsubq.multiapp.app.presentation.screen.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.multiapp.data.repository.WeatherRepositoryImpl.NetworkResult
import com.github.qsubq.multiapp.domain.model.WeatherModel
import com.github.qsubq.multiapp.domain.useCase.GetWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val getWeatherDataUseCase: GetWeatherDataUseCase) :
    ViewModel() {
    private val _weatherFlow = MutableStateFlow<NetworkResult<WeatherModel>>(NetworkResult.Loading())
    val weatherFlow: StateFlow<NetworkResult<WeatherModel>> = _weatherFlow

    fun getWeatherInfo(city: String) {
        viewModelScope.launch {
            _weatherFlow.value = getWeatherDataUseCase.invoke(city)
        }
    }
}