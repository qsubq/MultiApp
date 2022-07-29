package com.github.qsubq.multiapp.app.presentation.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.multiapp.data.repository.WeatherRepositoryImpl
import com.github.qsubq.multiapp.domain.model.*
import com.github.qsubq.multiapp.domain.useCase.GetWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val GetWeatherDataUseCase: GetWeatherDataUseCase) :
    ViewModel() {
    var weatherLiveData = MutableLiveData<WeatherModel>()
    var errorLiveData = MutableLiveData<String>()

    fun getWeatherInfo(city:String) {
        viewModelScope.launch {
            when (val response = GetWeatherDataUseCase.invoke(city)) {
                is WeatherRepositoryImpl.NetworkResult.Success ->  weatherLiveData.value = response.data
                is WeatherRepositoryImpl.NetworkResult.Error -> errorLiveData.value = "${response.code} ${response.message}"
                is WeatherRepositoryImpl.NetworkResult.Exception -> errorLiveData.value = "${response.e.message}"
            }
        }
    }


}