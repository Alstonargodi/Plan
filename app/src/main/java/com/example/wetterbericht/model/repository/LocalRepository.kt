package com.example.wetterbericht.model.repository

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.*
import com.example.wetterbericht.model.local.dao.TodoDao
import com.example.wetterbericht.model.local.database.LocalDatabase
import com.example.wetterbericht.model.local.entity.WeatherLocal
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class LocalRepository(database : LocalDatabase) {

    private val executorService = Executors.newSingleThreadExecutor()
    private val task = database.todoDao()
    private val weather = database.weatherDao()
    private val currentDate = LocalDateTime.now().dayOfMonth

    //task local
    fun readTodo() : LiveData<List<TodoLocal>> = task.readTodo()

    fun readChipAlarm() : LiveData<List<ChipAlarm>> = task.readAlarmChip()


    fun insertAlarmChip(alarm : ChipAlarm) =
        executorService.execute { task.insertAlarmChip(alarm) }

    fun readSearchTodo(name : String): LiveData<List<TodoLocal>> =
        task.readSearchTodo(name)

    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>> =
        task.getTodoSubtask(name)


    fun getTodayTaskReminder(): List<TodoLocal>{
      return task.getTodayTaskReminder(currentDate)
    }

    fun getTodayTask(): LiveData<List<TodoLocal>>{
      return task.getTodayTask(currentDate)
    }

    fun getUpcomingTask(): LiveData<List<TodoLocal>>{
        return task.getUpcomingTask(currentDate)
    }

    fun getPreviousTask(): LiveData<List<TodoLocal>>{
        return task.getPreviousTask(currentDate)
    }

    fun insertTodo(data : TodoLocal) =
        executorService.execute{ task.insertTodo(data) }

    fun insertSubtask(data : TodoSubTask){
        executorService.execute { task.insertSubTask(data) }
    }

    fun deleteTodo(name : String) =
        task.deleteTodo(name)


    //weather local
    fun getWeatherCityName(): WeatherLocal = weather.getWeatherLocationName()

    fun readWeather() : LiveData<List<WeatherLocal>> = weather.readWeather()

    fun insertWeather(data : WeatherLocal) =
        executorService.execute { weather.insertWeather(data) }

    fun searchLocation(name: String): LiveData<List<WeatherLocal>> =
        weather.searchLocation(name)

    fun deleteWeather(name : String) =
        weather.deleteWeather(name)






}