package com.example.wetterbericht.data.repository.remote

import com.example.wetterbericht.data.remote.openweather.forecast.ForecastResponse
import com.example.wetterbericht.data.remote.openweather.weather.WeatherResponse
import retrofit2.Call

interface IOpenWeatherRepository {
    fun getWeatherBySearch(loc : String): Call<WeatherResponse>
    fun getWeatherForecast(loc: Any): Call<ForecastResponse>
}