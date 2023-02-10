package com.example.wetterbericht.data.repository.local.weather

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.entities.weather.WeatherLocal

interface IWeatherLocalRepository {
    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>>
    fun getWeatherCityName(): WeatherLocal
    fun insertWeatherLocal(data : WeatherLocal)
    fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>>
}