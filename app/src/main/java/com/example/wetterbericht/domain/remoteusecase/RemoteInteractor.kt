package com.example.wetterbericht.domain.remoteusecase

import com.example.wetterbericht.data.remote.openweather.forecast.ForecastResponse
import com.example.wetterbericht.data.remote.openweather.weather.WeatherResponse
import com.example.wetterbericht.data.repository.remote.IRemoteRepository
import retrofit2.Call

class RemoteInteractor(private val iRemoteRepository: IRemoteRepository): RemoteUseCase {
    override fun getWeatherBySearch(loc: String): Call<WeatherResponse> {
       return iRemoteRepository.getWeatherBySearch(loc)
    }

    override fun getWeatherForecast(loc: Any): Call<ForecastResponse> {
       return iRemoteRepository.getWeatherForecast(loc)
    }
}