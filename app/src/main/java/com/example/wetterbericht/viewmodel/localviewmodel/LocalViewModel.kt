package com.example.wetterbericht.viewmodel.localviewmodel

import androidx.lifecycle.*
import com.example.wetterbericht.model.local.*
import com.example.wetterbericht.model.local.entity.habits.HabitsLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.model.local.entity.todolist.TodoandSubTask
import com.example.wetterbericht.model.local.entity.weather.WeatherLocal
import com.example.wetterbericht.model.repository.localrepository.LocalRepository

class LocalViewModel(private val repository: LocalRepository): ViewModel() {

    fun readTodoLocal(): LiveData<List<TodoLocal>> =
        repository.readTodo()

    fun readWeatherLocal(): LiveData<List<WeatherLocal>> =
        repository.readWeather()

    fun readAlarmChip(): LiveData<List<ChipAlarm>> =
        repository.readChipAlarm()


    fun readTodo(name: String): LiveData<List<TodoLocal>> =
        repository.readSearchTodo(name)


    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>> =
        repository.readTodoSubtask(name)


    fun getTodayTask(): LiveData<List<TodoLocal>> =
        repository.getTodayTask()

    fun getUpcomingTask(): LiveData<List<TodoLocal>> =
        repository.getUpcomingTask()

    fun getPreviousTask(): LiveData<List<TodoLocal>> =
        repository.getPreviousTask()

    fun insertTodoLocal(data : TodoLocal) = repository.insertTodo(data)


    fun insertSubtask(data : TodoSubTask) = repository.insertSubtask(data)


    fun insertWeatherLocal(data : WeatherLocal) = repository.insertWeather(data)


    fun insertAlarmChip(alarm : ChipAlarm) = repository.insertAlarmChip(alarm)


    fun searchLocation(name: String): LiveData<List<WeatherLocal>> =
        repository.searchLocation(name)


    fun deleteTodoLocal(name : String) = repository.deleteTodo(name)

    fun readHabits(): LiveData<List<HabitsLocal>> =
        repository.readHabits()

    fun insertHabits(data : HabitsLocal) =
        repository.insertHabits(data)

    fun deleteHabits(name : String)=
        repository.deleteHabits(name)

}