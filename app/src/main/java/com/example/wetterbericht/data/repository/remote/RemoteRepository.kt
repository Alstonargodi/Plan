package com.example.wetterbericht.data.repository.remote

import com.example.wetterbericht.data.remote.openweather.forecast.ForecastResponse
import com.example.wetterbericht.data.remote.openweather.weather.WeatherResponse
import com.example.wetterbericht.data.remote.source.WeatherDataSource
import retrofit2.Call

class RemoteRepository(
    private val remoteDataSource: WeatherDataSource
): IRemoteRepository {
    override fun getWeatherBySearch(loc: String): Call<WeatherResponse> {
        return remoteDataSource.getWeatherBySearch(loc)
    }

    override fun getWeatherForecast(loc: Any): Call<ForecastResponse> {
        return remoteDataSource.getWeatherForecast(loc)
    }
}