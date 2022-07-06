package com.example.wetterbericht.model.repository.weatherrepository

import com.example.wetterbericht.model.remote.openweather.forecast.ForecastResponse
import com.example.wetterbericht.model.remote.config.ApiConfig
import com.example.wetterbericht.model.remote.openweather.weather.WeatherResponse
import retrofit2.Call
import retrofit2.Response

class WeatherRepository {
    private val apiService = ApiConfig.getApiService()

    fun getWeatherBySearch(loc : Any): Call<WeatherResponse>{
        return apiService.getDataBySearch(loc)
    }


    fun getWeatherForecast(loc: Any): Call<ForecastResponse>{
        return apiService.getForecast(loc)
    }
}