package com.example.wetterbericht.viewmodel.localviewmodel

import androidx.lifecycle.*
import com.example.wetterbericht.domain.LocalUseCase
import com.example.wetterbericht.model.local.*
import com.example.wetterbericht.model.local.entity.habits.HabitsLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.model.local.entity.todolist.TodoandSubTask
import com.example.wetterbericht.model.local.entity.weather.WeatherLocal
import com.example.wetterbericht.model.repository.localrepository.LocalRepository

class LocalViewModel(
    private val repository: LocalRepository,
    private val todoUseCase: LocalUseCase
): ViewModel() {

    //onboarding
    fun getOnBoardingStatus(): LiveData<Boolean> =
        todoUseCase.getOnBoardingStatus()

    suspend fun saveOnBoardingStatus(onBoard : Boolean){
        todoUseCase.saveOnBoardingStatus(onBoard)
    }

    //todolist
    fun insertTodoLocal(data : TodoLocal) = todoUseCase.insertTodoList(data)

    fun insertAlarmChip(alarm : ChipAlarm) = todoUseCase.insertChipTime(alarm)

    fun insertSubtask(data : TodoSubTask) = todoUseCase.insertSubtask(data)

    fun readTodoLocalUse(): LiveData<List<TodoLocal>> =
        todoUseCase.readTodoLocal()

    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>> =
        todoUseCase.readTodoSubtask(name)

    fun getTodayTask(): LiveData<List<TodoLocal>> =
        todoUseCase.getTodayTask()

    fun getUpcomingTask(): LiveData<List<TodoLocal>> =
        todoUseCase.getUpComingTask()

    fun getPreviousTask(): LiveData<List<TodoLocal>> =
        todoUseCase.getPreviousTask()

    fun readAlarmChip(): LiveData<List<ChipAlarm>> =
        todoUseCase.readChipTime()

    fun deleteTodoLocal(name : String) = todoUseCase.deleteTodoList(name)

    //habits
    fun readHabits(): LiveData<List<HabitsLocal>> =
        repository.readHabits()

    fun insertHabits(data : HabitsLocal) =
        todoUseCase.insertHabitsLocal(data)

    fun deleteHabits(name : String)=
        repository.deleteHabits(name)

    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>> =
        repository.readWeather()

    fun insertWeatherLocal(data : WeatherLocal) =
        repository.insertWeather(data)

    fun searchLocation(name: String): LiveData<List<WeatherLocal>> =
        repository.searchLocation(name)
}