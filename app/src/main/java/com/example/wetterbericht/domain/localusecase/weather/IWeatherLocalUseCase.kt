package com.example.wetterbericht.domain.localusecase.weather

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.entities.weather.WeatherLocal

interface IWeatherLocalUseCase{

    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>>
    fun getWeatherCityName(): WeatherLocal
    fun insertWeatherLocal(data : WeatherLocal)
    fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>>
}