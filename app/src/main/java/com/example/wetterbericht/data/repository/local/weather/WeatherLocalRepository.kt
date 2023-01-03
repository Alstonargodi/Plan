package com.example.wetterbericht.data.repository.local.weather

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.source.weather.IWeatherLocalDataSource
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal

class WeatherLocalRepository(
    private val dataSource: IWeatherLocalDataSource
): IWeatherLocalRepository {

    override fun readWeatherLocal(): LiveData<List<WeatherLocal>> {
       return dataSource.readWeatherLocal()
    }

    override fun getWeatherCityName(): WeatherLocal {
        return dataSource.getWeatherByCityName()
    }

    override fun insertWeatherLocal(data: WeatherLocal) {
        dataSource.insertWeatherLocal(data)
    }

    override fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>> {
        return dataSource.searchWeatherLocal(name)
    }

}