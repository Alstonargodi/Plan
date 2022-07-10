package com.example.wetterbericht.model.repository.localrepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.wetterbericht.model.local.*
import com.example.wetterbericht.model.local.database.LocalDatabase
import com.example.wetterbericht.model.local.entity.habits.HabitsLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.model.local.entity.todolist.TodoandSubTask
import com.example.wetterbericht.model.local.entity.weather.WeatherLocal
import com.example.wetterbericht.model.local.preferences.OnboardingPreferences
import java.time.LocalDateTime
import java.util.concurrent.Executors


class LocalRepository(
    database : LocalDatabase,
    onBoardingPreferences: OnboardingPreferences
) {
    private val executorService = Executors.newSingleThreadExecutor()
    private val task = database.todoDao()
    private val habits = database.habitsDao()
    private val weather = database.weatherDao()
    private val currentDate = LocalDateTime.now().dayOfMonth
    private val preferences = onBoardingPreferences

    //onboarding
    fun getOnboardingStatus(): LiveData<Boolean> =
        preferences.getOnboardingStatus().asLiveData()

    suspend fun savePreferences(onBoard : Boolean){
        preferences.savePreferences(onBoard)
    }

    //time chip
    fun readChipAlarm() : LiveData<List<ChipAlarm>> = task.readAlarmChip()
    fun insertAlarmChip(alarm : ChipAlarm) =
        executorService.execute { task.insertAlarmChip(alarm) }

    //task
    fun readTodo() : LiveData<List<TodoLocal>> = task.readTodo()

    fun readSearchTodo(name : String): LiveData<List<TodoLocal>> =
        task.readSearchTodo(name)

    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>> =
        task.getTodoSubtask(name)

    fun insertTodo(data : TodoLocal) =
        executorService.execute{ task.insertTodo(data) }

    fun insertSubtask(data : TodoSubTask){
        executorService.execute { task.insertSubTask(data) }
    }

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

    fun deleteTodo(name : String) =
        task.deleteTodo(name)

    //habits
    fun readHabits(): LiveData<List<HabitsLocal>> = habits.readHabits()

    fun insertHabits(data : HabitsLocal) =
        executorService.execute { habits.insertHabits(data) }

    fun deleteHabits(name: String) =
        habits.deleteHabits(name)

    //weather
    fun getWeatherCityName(): WeatherLocal = weather.getWeatherLocationName()

    fun readWeather() : LiveData<List<WeatherLocal>> = weather.readWeather()

    fun insertWeather(data : WeatherLocal) =
        executorService.execute { weather.insertWeather(data) }

    fun searchLocation(name: String): LiveData<List<WeatherLocal>> =
        weather.searchLocation(name)




}