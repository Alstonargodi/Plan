package com.example.wetterbericht.data.local.source.weather

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.database.RoomDatabaseConfig
import com.example.wetterbericht.data.local.entities.weather.WeatherLocal
import java.util.concurrent.Executors

class WeatherLocalDataSource(
    val context: Context
) : IWeatherLocalDataSource {

    private val weatherDao = RoomDatabaseConfig.setInstance(context).weatherDao()
    private val executorService = Executors.newSingleThreadExecutor()

    override fun readWeatherLocal(): LiveData<List<WeatherLocal>> {
        return weatherDao.readWeather()
    }

    override fun getWeatherByCityName(): WeatherLocal {
        return weatherDao.getWeatherLocationName()
    }

    override fun insertWeatherLocal(data: WeatherLocal) {
        executorService.execute { weatherDao.insertWeather(data) }
    }

    override fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>> {
        return weatherDao.searchLocation(name)
    }
}