package com.example.wetterbericht.data.remote.source

import com.example.wetterbericht.data.remote.openweather.forecast.ForecastResponse
import com.example.wetterbericht.data.remote.openweather.weather.WeatherResponse
import com.example.wetterbericht.data.remote.service.WeatherService
import retrofit2.Call

class WeatherDataSource(private val weatherService: WeatherService){
    companion object{
        @Volatile
        private var instance : WeatherDataSource? = null

        fun getInstance(service : WeatherService): WeatherDataSource =
            instance ?: synchronized(this){
                instance ?: WeatherDataSource(service)
            }
    }

    fun getWeatherBySearch(loc : String): Call<WeatherResponse>{
        return weatherService.getDataBySearch(loc)
    }

    fun getWeatherForecast(loc: Any): Call<ForecastResponse>{
        return weatherService.getForecast(loc)
    }

}