package com.example.jatin.weatherapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jatin.weatherapp.api.ApiResult
import com.example.jatin.weatherapp.data.model.WeatherData
import com.example.jatin.weatherapp.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    suspend fun loadWeather(city: String): ApiResult<WeatherData?> {
        if (city.isEmpty())
            return ApiResult.Error("City name cannot be empty")
        return repository.getNewWeatherResponse(city = city)
    }

    suspend fun loadWeather(lat: String, lon: String): ApiResult<WeatherData?> {
        if (lat.isEmpty() || lon.isEmpty())
            return ApiResult.Error("City name cannot be empty")
        return repository.getNewWeatherResponse(lat = lat, lon = lon)
    }
}