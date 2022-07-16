package com.example.wetterbericht.viewmodel.localviewmodel

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.wetterbericht.domain.localusecase.LocalUseCase
import com.example.wetterbericht.data.local.*
import com.example.wetterbericht.data.local.entity.habits.HabitsLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.data.local.entity.todolist.TodoandSubTask
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

    //todolist
    fun readTodoTaskFilter(): LiveData<PagedList<TodoLocal>> =
        todoFilter.switchMap { todoUseCase.readTodoTaskFilter(it) }

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



    //habits
    fun getHabits(): LiveData<PagedList<HabitsLocal>> =
        habitsFilter.switchMap { todoUseCase.getHabits(it) }

    fun readHabits(): LiveData<List<HabitsLocal>> =
        todoUseCase.readHabitsLocal()

    fun insertHabits(data : HabitsLocal) =
        todoUseCase.insertHabitsLocal(data)

    fun deleteHabits(name : String)=
        todoUseCase.deleteHabitsLocal(name)

    fun filter(filter : HabitSortType){
        habitsFilter.value = filter
    }

    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>> =
        todoUseCase.readWeatherLocal()

    fun insertWeatherLocal(data : WeatherLocal) =
        todoUseCase.insertWeatherLocal(data)

    fun searchLocation(name: String): LiveData<List<WeatherLocal>> =
        todoUseCase.searchWeatherLocal(name)


}