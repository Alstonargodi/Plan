package com.example.wetterbericht.data.local.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entity.dailytask.TodoandSubTask
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal

interface ILocalDataSource {
    //onboarding
    fun getOnBoardingStatus(): LiveData<Boolean>
    suspend fun saveOnBoardingStatus(onBoard : Boolean)
    //chiptime
    fun readChipTime():LiveData<List<ChipAlarm>>
    fun insertChipTime(alarm: ChipAlarm)
    //todolist
    fun readNearestActiveTask(): TodoLocal
    fun readTodoTaskFilter(query: SupportSQLiteQuery): DataSource.Factory<Int,TodoLocal>
    fun insertTodoList(data : TodoLocal)
    fun insertSubtask(data : TodoSubTask)
    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>>
    fun readTodoLocal(): LiveData<List<TodoLocal>>
    fun getTodayTaskReminder(date : Int): List<TodoLocal>
    fun getTodayTask(date : Int): LiveData<List<TodoLocal>>
    fun getUpComingTask(date : Int): LiveData<List<TodoLocal>>
    fun getPreviousTask(date: Int): LiveData<List<TodoLocal>>
    fun deleteTodoList(name : String)
    fun updateTaskStatus(id : Int,status : Boolean)
    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>>
    fun getWeatherByCityName(): WeatherLocal
    fun insertWeatherLocal(data : WeatherLocal)
    fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>>
}