package com.example.wetterbericht.data.local.source

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.database.LocalDatabase
import com.example.wetterbericht.data.local.entity.habits.HabitsLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.data.local.entity.todolist.TodoandSubTask
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal
import com.example.wetterbericht.data.local.preferences.OnboardingPreferences
import com.example.wetterbericht.data.local.preferences.dataStore
import java.util.concurrent.Executors

class LocalDataSource(val context: Context) : ILocalDataSource {
    private val todoDao = LocalDatabase.setDatabase(context).todoDao()
    private val habitsDao = LocalDatabase.setDatabase(context).habitsDao()
    private val weatherDao = LocalDatabase.setDatabase(context).weatherDao()

    private val executorService = Executors.newSingleThreadExecutor()
    private val onBoardingPreferences = OnboardingPreferences.getInstance(context.dataStore)

    override fun getOnBoardingStatus(): LiveData<Boolean> {
        return onBoardingPreferences.getOnboardingStatus().asLiveData()
    }

    override suspend fun saveOnBoardingStatus(onBoard: Boolean) {
        onBoardingPreferences.savePreferences(onBoard)
    }

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
       return todoDao.readAlarmChip()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        executorService.execute { todoDao.insertAlarmChip(alarm) }
    }

    override fun readTodoSubtask(name: String): LiveData<List<TodoandSubTask>> {
        return todoDao.getTodoSubtask(name)
    }

    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return todoDao.readTodo()
    }

    override fun getTodayTask(date : Int): LiveData<List<TodoLocal>> {
       return todoDao.getTodayTask(date)
    }

    override fun getUpComingTask(date: Int): LiveData<List<TodoLocal>> {
        return todoDao.getUpcomingTask(date)
    }

    override fun getPreviousTask(date: Int): LiveData<List<TodoLocal>> {
        return todoDao.getPreviousTask(date)
    }

    override fun getTodayTaskReminder(date : Int): List<TodoLocal> {
        return todoDao.getTodayTaskReminder(date)
    }

    override fun insertTodoList(data: TodoLocal) {
        executorService.execute { todoDao.insertTodo(data) }
    }

    override fun insertSubtask(data: TodoSubTask) {
        executorService.execute { todoDao.insertSubTask(data) }
    }

    override fun deleteTodoList(name: String) {
        todoDao.deleteTodo(name)
    }

    override fun readHabitsLocal(): LiveData<List<HabitsLocal>> {
       return habitsDao.readHabits()
    }

    override fun insertHabitsLocal(data: HabitsLocal) {
       executorService.execute { habitsDao.insertHabits(data) }
    }

    override fun deleteHabitsLocal(name: String) {
        habitsDao.deleteHabits(name)
    }

    override fun readWeatherLocal(): LiveData<List<WeatherLocal>> {
        return weatherDao.readWeather()
    }

    override fun getWeatherCityname(): WeatherLocal {
        return weatherDao.getWeatherLocationName()
    }

    override fun insertWeatherLocal(data: WeatherLocal) {
        executorService.execute { weatherDao.insertWeather(data) }
    }

    override fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>> {
        return weatherDao.searchLocation(name)
    }


}