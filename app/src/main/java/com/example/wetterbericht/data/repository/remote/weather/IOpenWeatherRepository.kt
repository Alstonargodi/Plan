package com.example.wetterbericht.data.repository.remote.weather

import com.example.wetterbericht.data.remote.weather.openweather.forecast.ForecastResponse
import com.example.wetterbericht.data.remote.weather.openweather.weather.WeatherResponse
import retrofit2.Call

interface IOpenWeatherRepository {
    fun getWeatherBySearch(loc : String): Call<WeatherResponse>
    fun getWeatherForecast(loc: Any): Call<ForecastResponse>
}