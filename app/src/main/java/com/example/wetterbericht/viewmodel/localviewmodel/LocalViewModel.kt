package com.example.wetterbericht.viewmodel.localviewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.wetterbericht.domain.localusecase.LocalUseCase
import com.example.wetterbericht.data.local.*
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entity.dailytask.TodoandSubTask
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal
import com.example.wetterbericht.helpers.sortfilter.HabitSortType
import com.example.wetterbericht.helpers.sortfilter.TodoSortType

class LocalViewModel(
    private val todoUseCase: LocalUseCase
): ViewModel() {
    private val habitsFilter = MutableLiveData<HabitSortType>()
    private val todoFilter = MutableLiveData<TodoSortType>()
    val snackbarEvent = MutableLiveData<String>()

    init {
        habitsFilter.value = HabitSortType.START_TIME
        todoFilter.value = TodoSortType.ALL_TASKS
    }

    //onboarding
    fun getOnBoardingStatus(): LiveData<Boolean> =
        todoUseCase.getOnBoardingStatus()

    suspend fun saveOnBoardingStatus(onBoard : Boolean){
        todoUseCase.saveOnBoardingStatus(onBoard)
    }

    fun taskFilter(filter :TodoSortType){
        todoFilter.value = filter
    }

    //todolist
    fun readTodoTaskFilter(): LiveData<PagedList<TodoLocal>> =
        todoFilter.switchMap {
            todoUseCase.readTodoTaskFilter(it)
        }

    fun readTodoLocalUse(): LiveData<List<TodoLocal>> =
        todoUseCase.readTodoLocal()

    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>> {
        Log.d("today $name", todoUseCase.readTodoSubtask(name).value.toString())
        return todoUseCase.readTodoSubtask(name)
    }

    fun getTodayTask(): LiveData<List<TodoLocal>> =
        todoUseCase.getTodayTask()

    fun getUpcomingTask(): LiveData<List<TodoLocal>> =
        todoUseCase.getUpComingTask()

    fun getPreviousTask(): LiveData<List<TodoLocal>> =
        todoUseCase.getPreviousTask()

    fun readAlarmChip(): LiveData<List<ChipAlarm>> =
        todoUseCase.readChipTime()

    fun insertTodoLocal(data : TodoLocal) =
        todoUseCase.insertTodoList(data)

    fun insertAlarmChip(alarm : ChipAlarm) =
        todoUseCase.insertChipTime(alarm)

    fun insertSubtask(data : TodoSubTask) =
        todoUseCase.insertSubtask(data)

    fun updateTask(taskId : Int,completed : Boolean){
        todoUseCase.updateTaskStatus(taskId,completed)
    }

    fun deleteTodoLocal(name : String){
        todoUseCase.deleteTodoList(name)
        snackbarEvent.value = name
    }



    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>> =
        todoUseCase.readWeatherLocal()

    fun insertWeatherLocal(data : WeatherLocal) =
        todoUseCase.insertWeatherLocal(data)

    fun searchLocation(name: String): LiveData<List<WeatherLocal>> =
        todoUseCase.searchWeatherLocal(name)


}