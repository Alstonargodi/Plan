package com.example.wetterbericht.data.local.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.habits.HabitsLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.data.local.entity.todolist.TodoandSubTask
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
    //habits
    fun getHabits(query: SupportSQLiteQuery): DataSource.Factory<Int,HabitsLocal>
    fun readHabitsLocal(): LiveData<List<HabitsLocal>>
    fun insertHabitsLocal(data: HabitsLocal)
    fun deleteHabitsLocal(name: String)
    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>>
    fun getWeatherByCityName(): WeatherLocal
    fun insertWeatherLocal(data : WeatherLocal)
    fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>>
}