package com.example.jatin.weatherapp.data.repository

import android.util.Log
import com.example.jatin.weatherapp.api.ApiResult
import com.example.jatin.weatherapp.api.OpenWeatherApi
import com.example.jatin.weatherapp.data.model.WeatherData
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val openWeatherApi: OpenWeatherApi
) {

//    suspend fun getNewWeatherResponse(city: String): ApiResult<WeatherData?> {
//        val weatherApiResponse = try {    // if it has internet connection
//            val response = openWeatherApi.getWeather(query = city)
//            if (response.isSuccessful) {
//                ApiResult.Success(response.body())
//            } else {
//                ApiResult.Error(response.message())
//            }
//        } catch (e: Exception) {
//            Log.e(javaClass.name, "getWeatherResponse($city): $e")
//            Log.e(javaClass.name, "getWeatherResponse($city): ${e.localizedMessage}")
//            ApiResult.Error(message = e.localizedMessage)
//        }
//        return weatherApiResponse
//    }

    suspend fun getNewWeatherResponse(
        city: String? = null,
        lat: String? = null,
        lon: String? = null
    ): ApiResult<WeatherData?> {
        val weatherApiResponse = try {    // if it has internet connection
            val response =
                if (city != null)
                    openWeatherApi.getWeather(query = city)
                else if (lat != null && lon != null)
                    openWeatherApi.getWeather(lat = lat, lon = lon)
                else
                    throw Exception("Incorrect city or latitude and longitude combination")
            println("response getWeatherResponse($lat, $lon): $response")
            if (response.isSuccessful) {
                ApiResult.Success(response.body())
            } else {
                ApiResult.Error(response.message())
            }
        } catch (e: Exception) {
            Log.e(javaClass.name, "getWeatherResponse($city): $e")
            Log.e(javaClass.name, "getWeatherResponse($city): ${e.localizedMessage}")
            ApiResult.Error(message = e.localizedMessage)
        }
        return weatherApiResponse
    }

}
