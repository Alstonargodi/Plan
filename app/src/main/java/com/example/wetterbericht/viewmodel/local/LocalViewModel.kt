package com.example.wetterbericht.viewmodel.local

import android.app.Application
import androidx.lifecycle.*
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.model.local.repository.TodoRepository
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.model.local.WeatherLocal

class LocalViewModel(application: Application): ViewModel() {
    private val todoRepo : TodoRepository = TodoRepository(application)

    var responseTodoLocal : LiveData<List<TodoLocal>> = MutableLiveData()
    var responseWeatherLocal : LiveData<List<WeatherLocal>> = MutableLiveData()
    var responseAlarmChip : LiveData<List<ChipAlarm>> = MutableLiveData()


    fun readTodoLocal(){
        responseTodoLocal = todoRepo.readTodo
    }
    fun readWeatherLocal(){
        responseWeatherLocal = todoRepo.readWeather
    }
    fun readAlarmChip(){
        responseAlarmChip = todoRepo.readChipAlarm
    }

    fun insertTodoLocal(data : TodoLocal){
        todoRepo.insertTodo(data)
    }

    fun insertWeatherLocal(data : WeatherLocal){
        todoRepo.insertWeather(data)
    }

    fun insertAlarmChip(alarm : ChipAlarm){
        todoRepo.insertAlarmChip(alarm)
    }

    fun deleteTodoLocal(name : String){
        todoRepo.deleteTodo(name)
    }

    fun deleteWeatherLocal(name : String){
        todoRepo.deleteWeather(name)
    }


}