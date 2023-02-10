package com.example.wetterbericht.domain.remoteusecase

import com.example.wetterbericht.data.remote.openweather.forecast.ForecastResponse
import com.example.wetterbericht.data.remote.openweather.weather.WeatherResponse
import com.example.wetterbericht.data.repository.remote.IOpenWeatherRepository
import retrofit2.Call

class OpenWeatherUseCase(private val iOpenWeatherRepository: IOpenWeatherRepository): IOpenWeatherUseCase {
    override fun getWeatherBySearch(loc: String): Call<WeatherResponse> {
       return iOpenWeatherRepository.getWeatherBySearch(loc)
    }

    override fun getWeatherForecast(loc: Any): Call<ForecastResponse> {
       return iOpenWeatherRepository.getWeatherForecast(loc)
    }
}