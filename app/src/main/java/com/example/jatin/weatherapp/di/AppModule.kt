package com.example.jatin.weatherapp.di

import com.example.jatin.weatherapp.api.OpenWeatherApi
import com.example.jatin.weatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    @Singleton
//    @Provides
//    fun provideOpenWeatherApi(): OpenWeatherApi {
//        return Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(OpenWeatherApi::class.java)
//    }

    @Singleton
    @Provides
    fun provideOpenWeatherApi(): OpenWeatherApi {
        return OpenWeatherApi.create()
    }
}