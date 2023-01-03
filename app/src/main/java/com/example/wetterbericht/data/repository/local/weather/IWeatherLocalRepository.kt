package com.example.wetterbericht.data.repository.local.weather

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entity.dailytask.TodoandSubTask
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal

interface IWeatherLocalRepository {
    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>>
    fun getWeatherCityName(): WeatherLocal
    fun insertWeatherLocal(data : WeatherLocal)
    fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>>
}