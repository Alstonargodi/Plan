package com.example.wetterbericht.data.local.source

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
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
    private val todoDao = LocalDatabase.setInstance(context).todoDao()
    private val habitsDao = LocalDatabase.setInstance(context).habitsDao()
    private val weatherDao = LocalDatabase.setInstance(context).weatherDao()

    private val executorService = Executors.newSingleThreadExecutor()
    private val onBoardingPreferences = OnboardingPreferences.getInstance(context.dataStore)

    override fun getOnBoardingStatus(): LiveData<Boolean> {
        return onBoardingPreferences.getOnboardingStatus().asLiveData()
    }

    override suspend fun saveOnBoardingStatus(onBoard: Boolean) {
        onBoardingPreferences.savePreferences(onBoard)
    }

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
       return todoDao.readTimeChip()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        executorService.execute { todoDao.insertTimeChip(alarm) }
    }

    override fun readNearestActiveTask(): TodoLocal {
        return todoDao.readNearestActiveTask()
    }

    override fun readTodoTaskFilter(query: SupportSQLiteQuery): DataSource.Factory<Int, TodoLocal> {
        return todoDao.readTodoTaskFilter(query)
    }

    override fun readTodoSubtask(name: String): LiveData<List<TodoandSubTask>> {
        return todoDao.readSubTaskTodoList(name)
    }

    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return todoDao.readTodoList()
    }

    override fun getTodayTask(date : Int): LiveData<List<TodoLocal>> {
       return todoDao.readTodayTask(date)
    }

    override fun getUpComingTask(date: Int): LiveData<List<TodoLocal>> {
        return todoDao.readUpcomingTask(date)
    }

    override fun getPreviousTask(date: Int): LiveData<List<TodoLocal>> {
        return todoDao.readPreviousTask(date)
    }

    override fun getTodayTaskReminder(date : Int): List<TodoLocal> {
        return todoDao.readTodayTaskReminder(date)
    }

    override fun insertTodoList(data: TodoLocal) {
        executorService.execute { todoDao.insertTodoList(data) }
    }

    override fun insertSubtask(data: TodoSubTask) {
        executorService.execute { todoDao.insertSubTask(data) }
    }

    override fun deleteTodoList(name: String) {
        executorService.execute { todoDao.deleteTodoTask(name) }
    }

    override fun updateTaskStatus(id: Int, status: Boolean) {
       executorService.execute { todoDao.updateTaskStatus(id, status) }
    }

    override fun getHabits(query: SupportSQLiteQuery): DataSource.Factory<Int, HabitsLocal> {
        return habitsDao.getHabits(query)
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