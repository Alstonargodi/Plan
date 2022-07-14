package com.example.wetterbericht.viewmodel.localviewmodel

import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.domain.localusecase.LocalUseCase
import com.example.wetterbericht.data.local.*
import com.example.wetterbericht.data.local.entity.habits.HabitsLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.data.local.entity.todolist.TodoandSubTask
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal
import com.example.wetterbericht.helpers.sortfilter.HabitSortType

class LocalViewModel(
    private val todoUseCase: LocalUseCase
): ViewModel() {
    private val _filter = MutableLiveData<HabitSortType>()

    init {
        _filter.value = HabitSortType.START_TIME
    }

    //onboarding
    fun getOnBoardingStatus(): LiveData<Boolean> =
        todoUseCase.getOnBoardingStatus()

    suspend fun saveOnBoardingStatus(onBoard : Boolean){
        todoUseCase.saveOnBoardingStatus(onBoard)
    }

    //todolist
    fun insertTodoLocal(data : TodoLocal) =
        todoUseCase.insertTodoList(data)

    fun insertAlarmChip(alarm : ChipAlarm) =
        todoUseCase.insertChipTime(alarm)

    fun insertSubtask(data : TodoSubTask) =
        todoUseCase.insertSubtask(data)

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

    fun deleteTodoLocal(name : String) =
        todoUseCase.deleteTodoList(name)

    //habits
    fun getHabits(): LiveData<PagedList<HabitsLocal>> =
        _filter.switchMap { todoUseCase.getHabits(it) }

    fun readHabits(): LiveData<List<HabitsLocal>> =
        todoUseCase.readHabitsLocal()

    fun insertHabits(data : HabitsLocal) =
        todoUseCase.insertHabitsLocal(data)

    fun deleteHabits(name : String)=
        todoUseCase.deleteHabitsLocal(name)

    //weather
    fun readWeatherLocal(): LiveData<List<WeatherLocal>> =
        todoUseCase.readWeatherLocal()

    fun insertWeatherLocal(data : WeatherLocal) =
        todoUseCase.insertWeatherLocal(data)

    fun searchLocation(name: String): LiveData<List<WeatherLocal>> =
        todoUseCase.searchWeatherLocal(name)

    fun filter(filter : HabitSortType){
        _filter.value = filter
    }
}