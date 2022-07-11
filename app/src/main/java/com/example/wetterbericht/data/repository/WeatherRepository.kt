package com.example.wetterbericht.data.repository

import com.example.wetterbericht.data.remote.openweather.forecast.ForecastResponse
import com.example.wetterbericht.data.remote.config.ApiConfig
import com.example.wetterbericht.data.remote.openweather.weather.WeatherResponse
import retrofit2.Call

class WeatherRepository {
    private val apiService = ApiConfig.getApiService()

    fun getWeatherBySearch(loc : Any): Call<WeatherResponse>{
        return apiService.getDataBySearch(loc)
    }

    fun getWeatherForecast(loc: Any): Call<ForecastResponse>{
        return apiService.getForecast(loc)
    }
}