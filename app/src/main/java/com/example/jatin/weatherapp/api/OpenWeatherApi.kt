package com.example.jatin.weatherapp.api

import com.example.jatin.weatherapp.data.model.WeatherData
import com.example.jatin.weatherapp.utils.Constants.API_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface OpenWeatherApi {

    @GET(value = "data/2.5/weather")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") api_id: String = API_KEY
    ): Response<WeatherData>

    @GET(value = "data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = "imperial",
        @Query("appid") api_id: String = API_KEY
    ): Response<WeatherData>

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/"

        fun create(): OpenWeatherApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherApi::class.java)

        }
    }
}

