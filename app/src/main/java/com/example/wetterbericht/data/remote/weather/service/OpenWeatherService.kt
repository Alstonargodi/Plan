package com.example.wetterbericht.data.remote.weather.service

import com.example.wetterbericht.data.remote.weather.openweather.forecast.ForecastResponse
import com.example.wetterbericht.data.remote.weather.openweather.weather.WeatherResponse
import com.example.wetterbericht.data.remote.weather.utils.ApiUtils.weatherApiKey
import com.example.wetterbericht.data.remote.weather.utils.ApiUtils.weatherUnit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("weather")
    fun getDataBySearch(
        @Query("q") Location : Any,
        @Query("units") units : String = weatherUnit,
        @Query("appid") api_key : String = weatherApiKey
    ): Call<WeatherResponse>

    @GET("forecast")
    fun getForecast(
        @Query("q") Location: Any,
        @Query("units") units : String = weatherUnit,
        @Query("appid") apikey : String = weatherApiKey
    ): Call<ForecastResponse>
}