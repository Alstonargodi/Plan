package com.example.wetterbericht.model.remote

import com.example.wetterbericht.model.remote.response.ForecastResponse
import com.example.wetterbericht.model.remote.ApiConfig
import com.example.wetterbericht.model.remote.service.WeatherResponse
import retrofit2.Call
import retrofit2.Response

class WeatherRepository(){
    private val apiService = ApiConfig.getApiService()

    fun getWeatherBySearch(loc : Any): Call<WeatherResponse>{
        return apiService.getDataBySearch(loc)
    }

    suspend fun getWeatherByLocation(lat : Double, lon : Double): Response<WeatherResponse>{
        return apiService.getDatabyLocation(lat,lon)
    }

    fun getWeatherForecast(loc: Any): Call<ForecastResponse>{
        return apiService.getForecast(loc)
    }
}