package com.example.wetterbericht.data.remote.weather.source

import com.example.wetterbericht.data.remote.weather.openweather.forecast.ForecastResponse
import com.example.wetterbericht.data.remote.weather.openweather.weather.WeatherResponse
import com.example.wetterbericht.data.remote.weather.service.OpenWeatherService
import retrofit2.Call

class OpenWeatherDataSource(
    private val openWeatherService: OpenWeatherService
 ){
    companion object{
        @Volatile
        private var instance : OpenWeatherDataSource? = null

        fun getInstance(service : OpenWeatherService): OpenWeatherDataSource =
            instance ?: synchronized(this){
                instance ?: OpenWeatherDataSource(service)
            }
    }

    fun getWeatherBySearch(loc : String): Call<WeatherResponse>{
        return openWeatherService.getDataBySearch(loc)
    }

    fun getWeatherForecast(loc: Any): Call<ForecastResponse>{
        return openWeatherService.getForecast(loc)
    }
}