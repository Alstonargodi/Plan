package com.example.wetterbericht.data.local.source.weather

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.entities.weather.WeatherLocal

interface IWeatherLocalDataSource {
    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>>
    fun getWeatherByCityName(): WeatherLocal
    fun insertWeatherLocal(data : WeatherLocal)
    fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>>
}