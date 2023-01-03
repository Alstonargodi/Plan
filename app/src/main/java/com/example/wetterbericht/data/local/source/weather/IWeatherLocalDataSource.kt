package com.example.wetterbericht.data.local.source.weather

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entity.dailytask.TodoandSubTask
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal

interface IWeatherLocalDataSource {
    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>>
    fun getWeatherByCityName(): WeatherLocal
    fun insertWeatherLocal(data : WeatherLocal)
    fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>>
}