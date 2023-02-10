package com.example.wetterbericht.data.repository.remote

import com.example.wetterbericht.data.remote.openweather.forecast.ForecastResponse
import com.example.wetterbericht.data.remote.openweather.weather.WeatherResponse
import com.example.wetterbericht.data.remote.source.OpenWeatherDataSource
import retrofit2.Call

class OpenWeatherRepository(
    private val remoteDataSource: OpenWeatherDataSource
): IOpenWeatherRepository {
    override fun getWeatherBySearch(loc: String): Call<WeatherResponse> {
        return remoteDataSource.getWeatherBySearch(loc)
    }

    override fun getWeatherForecast(loc: Any): Call<ForecastResponse> {
        return remoteDataSource.getWeatherForecast(loc)
    }
}