package com.example.wetterbericht.domain.localusecase.weather

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.entities.weather.WeatherLocal
import com.example.wetterbericht.data.repository.local.weather.IWeatherLocalRepository

//where business logic
class IWeatherLocalLocalUseCase(private val repository: IWeatherLocalRepository): IWeatherLocalUseCase {
    override fun readWeatherLocal(): LiveData<List<WeatherLocal>> {
        return repository.readWeatherLocal()
    }

    override fun getWeatherCityName(): WeatherLocal {
        return repository.getWeatherCityName()
    }

    override fun insertWeatherLocal(data: WeatherLocal) {
        repository.insertWeatherLocal(data)
    }

    override fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>> {
       return repository.searchWeatherLocal(name)
    }
}