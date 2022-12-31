package com.example.wetterbericht.domain.localusecase.weather

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entity.dailytask.TodoandSubTask
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal
import com.example.wetterbericht.helpers.sortfilter.HabitSortType
import com.example.wetterbericht.helpers.sortfilter.TodoSortType

interface WeatherUseCase{

    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>>
    fun getWeatherCityName(): WeatherLocal
    fun insertWeatherLocal(data : WeatherLocal)
    fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>>
}