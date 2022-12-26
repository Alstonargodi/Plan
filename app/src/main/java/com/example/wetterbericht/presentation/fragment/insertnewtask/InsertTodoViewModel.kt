package com.example.wetterbericht.presentation.fragment.insertnewtask

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
import com.example.wetterbericht.domain.localusecase.todotask.TodoLocalUseCase
import com.example.wetterbericht.helpers.sortfilter.HabitSortType
import com.example.wetterbericht.helpers.sortfilter.TodoSortType

class InsertTodoViewModel(
    private val useCase: TodoLocalUseCase
): ViewModel() {
    val snackbarEvent = MutableLiveData<String>()
    private val todoFilter = MutableLiveData<TodoSortType>()

    init {
        todoFilter.value = TodoSortType.ALL_TASKS
    }

    fun readTodoTaskFilter(): LiveData<PagedList<TodoLocal>> =
        todoFilter.switchMap {
            useCase.readTodoTaskFilter(it)
        }

    fun readTodoLocalUse(): LiveData<List<TodoLocal>> =
        useCase.readTodoLocal()

    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>> {
        Log.d("today $name", useCase.readTodoSubtask(name).value.toString())
        return useCase.readTodoSubtask(name)
    }

    fun getTodayTask(): LiveData<List<TodoLocal>> =
        useCase.getTodayTask()

    fun getUpcomingTask(): LiveData<List<TodoLocal>> =
        useCase.getUpComingTask()

    fun getPreviousTask(): LiveData<List<TodoLocal>> =
        useCase.getPreviousTask()

    fun readAlarmChip(): LiveData<List<ChipAlarm>> =
        useCase.readChipTime()

    fun insertTodoLocal(data : TodoLocal) =
        useCase.insertTodoList(data)

    fun insertAlarmChip(alarm : ChipAlarm) =
        useCase.insertChipTime(alarm)

    fun insertSubtask(data : TodoSubTask) =
        useCase.insertSubtask(data)

    fun updateTask(taskId : Int,completed : Boolean){
        useCase.updateTaskStatus(taskId,completed)
    }

    fun deleteTodoLocal(name : String){
        useCase.deleteTodoList(name)
        snackbarEvent.value = name
    }

    fun taskFilter(filter :TodoSortType){
        todoFilter.value = filter
    }

}