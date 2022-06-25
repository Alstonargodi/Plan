package com.example.wetterbericht.viewmodel.local

import android.app.Application
import androidx.lifecycle.*
import com.example.wetterbericht.model.local.*
import com.example.wetterbericht.model.repository.LocalRepository

class LocalViewModel(application: Application): ViewModel() {
    private val todoRepo : LocalRepository = LocalRepository(application)

    var responseTodoLocal : LiveData<List<TodoLocal>> = MutableLiveData()
    var responseWeatherLocal : LiveData<List<WeatherLocal>> = MutableLiveData()
    var responseAlarmChip : LiveData<List<ChipAlarm>> = MutableLiveData()
    var responseTodoandSubtask : LiveData<List<TodoandSubTask>> = MutableLiveData()
    var responseTodoSearch : LiveData<List<TodoLocal>> = MutableLiveData()


    fun readTodoLocal(){
        responseTodoLocal = todoRepo.readTodo
    }

    fun readWeatherLocal(){
        responseWeatherLocal = todoRepo.readWeather
    }

    fun readAlarmChip(){
        responseAlarmChip = todoRepo.readChipAlarm
    }

    fun readTodo(name: String){
        responseTodoSearch = todoRepo.readTodo(name)
    }

    fun readTodoandSubtask(name : String){
        responseTodoandSubtask = todoRepo.readTodoSubtask(name)
    }


    fun insertTodoLocal(data : TodoLocal){
        todoRepo.insertTodo(data)
    }

    fun insertSubtask(data : TodoSubTask){
        todoRepo.insertSubtask(data)
    }

    fun insertWeatherLocal(data : WeatherLocal){
        todoRepo.insertWeather(data)
    }

    fun insertAlarmChip(alarm : ChipAlarm){
        todoRepo.insertAlarmChip(alarm)
    }

    fun searchLocation(name: String): LiveData<List<WeatherLocal>> =
        todoRepo.searchLocation(name)

    fun deleteTodoLocal(name : String){
        todoRepo.deleteTodo(name)
    }

    fun deleteWeatherLocal(name : String){
        todoRepo.deleteWeather(name)
    }


}