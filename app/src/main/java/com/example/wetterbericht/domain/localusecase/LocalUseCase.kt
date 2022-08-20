package com.example.wetterbericht.domain.localusecase

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

interface LocalUseCase{
    //onboarding
    fun getOnBoardingStatus(): LiveData<Boolean>
    suspend fun saveOnBoardingStatus(onBoard : Boolean)
    //time chip
    fun readChipTime():LiveData<List<ChipAlarm>>
    fun insertChipTime(alarm: ChipAlarm)
    //todolist
    fun readNearestActiveTask(): TodoLocal
    fun readTodoTaskFilter(query: TodoSortType): LiveData<PagedList<TodoLocal>>
    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>>
    fun readTodoLocal(): LiveData<List<TodoLocal>>
    fun getTodayTaskReminder(): List<TodoLocal>
    fun getTodayTask(): LiveData<List<TodoLocal>>
    fun getUpComingTask(): LiveData<List<TodoLocal>>
    fun getPreviousTask(): LiveData<List<TodoLocal>>
    fun insertTodoList(data : TodoLocal)
    fun insertSubtask(data : TodoSubTask)
    fun deleteTodoList(name : String)
    fun updateTaskStatus(id : Int,status : Boolean)
    //habits
    fun getHabits(filter : HabitSortType): LiveData<PagedList<DailyHabits>>
    fun readHabitsLocal(): LiveData<List<DailyHabits>>
    fun insertHabitsLocal(data: DailyHabits)
    fun deleteHabitsLocal(name: String)
    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>>
    fun getWeatherCityName(): WeatherLocal
    fun insertWeatherLocal(data : WeatherLocal)
    fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>>
}