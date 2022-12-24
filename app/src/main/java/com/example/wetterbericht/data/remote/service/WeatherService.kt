package com.example.wetterbericht.data.remote.service

import com.example.wetterbericht.data.remote.openweather.forecast.ForecastResponse
import com.example.wetterbericht.data.remote.openweather.weather.WeatherResponse
import com.example.wetterbericht.data.remote.utils.Constant.weatherApiKey
import com.example.wetterbericht.data.remote.utils.Constant.weatherUnit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
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