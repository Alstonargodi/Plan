package com.example.wetterbericht.domain.localusecase

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.habits.HabitsLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.data.local.entity.todolist.TodoandSubTask
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal

interface LocalUseCase{
    //onboarding
    fun getOnBoardingStatus(): LiveData<Boolean>
    suspend fun saveOnBoardingStatus(onBoard : Boolean)
    //time chip
    fun readChipTime():LiveData<List<ChipAlarm>>
    fun insertChipTime(alarm: ChipAlarm)
    //todolist
    fun insertTodoList(data : TodoLocal)
    fun insertSubtask(data : TodoSubTask)
    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>>
    fun readTodoLocal(): LiveData<List<TodoLocal>>
    fun getTodayTaskReminder(): List<TodoLocal>
    fun getTodayTask(): LiveData<List<TodoLocal>>
    fun getUpComingTask(): LiveData<List<TodoLocal>>
    fun getPreviousTask(): LiveData<List<TodoLocal>>
    fun deleteTodoList(name : String)
    //habits
    fun readHabitsLocal(): LiveData<List<HabitsLocal>>
    fun insertHabitsLocal(data: HabitsLocal)
    fun deleteHabitsLocal(name: String)
    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>>
    fun getWeatherCityname(): WeatherLocal
    fun insertWeatherLocal(data : WeatherLocal)
    fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>>
}