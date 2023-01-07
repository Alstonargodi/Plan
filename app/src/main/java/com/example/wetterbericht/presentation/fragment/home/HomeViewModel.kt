package com.example.wetterbericht.presentation.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entity.dailytask.TodoandSubTask
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal
import com.example.wetterbericht.domain.localusecase.weather.WeatherUseCase
import com.example.wetterbericht.domain.localusecase.todotask.TodoLocalUseCase
import com.example.wetterbericht.helpers.sortfilter.TodoSortType

class HomeViewModel(
    private val todoUseCase: TodoLocalUseCase,
    private val weatherUseCase : WeatherUseCase
):ViewModel() {
    val snackbarEvent = MutableLiveData<String>()
    private val todoFilter = MutableLiveData<TodoSortType>()

    init {
        todoFilter.value = TodoSortType.ALL_TASKS
    }

    fun readTodoTaskFilter(): LiveData<PagedList<TodoLocal>> =
        todoFilter.switchMap {
            todoUseCase.readTodoTaskFilter(it)
        }

    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>> {
        Log.d("today $name", todoUseCase.readTodoSubtask(name).value.toString())
        return todoUseCase.readTodoSubtask(name)
    }

    fun getUpcomingTask(): LiveData<List<TodoLocal>> =
        todoUseCase.getUpComingTask()

    fun getPreviousTask(): LiveData<List<TodoLocal>> =
        todoUseCase.getPreviousTask()


    fun insertSubtask(data : TodoSubTask) =
        todoUseCase.insertSubtask(data)

    fun updateTask(taskId : Int,completed : Boolean){
        todoUseCase.updateTaskStatus(taskId,completed)
    }

    fun updateSubtask(id : Int,status: Boolean){
        todoUseCase.updateSubtask(id, status)
    }

    fun deleteTodoLocal(name : String){
        todoUseCase.deleteTodoList(name)
        snackbarEvent.value = name
    }

    fun taskFilter(filter : TodoSortType){
        todoFilter.value = filter
    }

    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>> =
        weatherUseCase.readWeatherLocal()

    fun insertWeatherLocal(data : WeatherLocal) =
        weatherUseCase.insertWeatherLocal(data)


}