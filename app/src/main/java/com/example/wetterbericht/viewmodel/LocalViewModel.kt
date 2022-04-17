package com.example.wetterbericht.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.wetterbericht.model.local.LocalRepository
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.model.local.WeatherLocal

class LocalViewModel(application: Application): ViewModel() {
    private val localRepo : LocalRepository = LocalRepository(application)

    var responTodoLocal : LiveData<List<TodoLocal>> = MutableLiveData()
    var responWeatherLocal : LiveData<List<WeatherLocal>> = MutableLiveData()

    fun readTodoLocal(){
        responTodoLocal = localRepo.readTodo
    }
    fun readWeatherLocal(){
        responWeatherLocal = localRepo.readWeather
    }

    fun insertTodoLocal(data : TodoLocal){
        localRepo.insertTodo(data)
    }

    fun insertWeatherLocal(data : WeatherLocal){
        localRepo.insertWeather(data)
    }

    fun deleteTodoLocal(name : String){
        localRepo.deleteTodo(name)
    }

    fun deleteWeatherLocal(name : String){
        localRepo.deleteWeather(name)
    }


}