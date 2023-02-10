package com.example.wetterbericht.presentation.fragment.insertnewtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entities.dailyhabits.ColorHabits
import com.example.wetterbericht.data.local.entities.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entities.dailytask.TodoSubTask
import com.example.wetterbericht.domain.localusecase.habits.HabitsLocalUseCase
import com.example.wetterbericht.domain.localusecase.todotask.TodoLocalUseCase
import com.example.wetterbericht.helpers.sortfilter.TodoSortType

class InsertTodoViewModel(
    private val todoUseCase: TodoLocalUseCase,
    private val colorUseCase : HabitsLocalUseCase
): ViewModel() {
    private val todoFilter = MutableLiveData<TodoSortType>()

    init {
        todoFilter.value = TodoSortType.ALL_TASKS
    }

    fun readTodoTaskFilter(): LiveData<PagedList<TodoLocal>> =
        todoFilter.switchMap {
            todoUseCase.readTodoTaskFilter(it)
        }

    fun readColorList(): LiveData<List<ColorHabits>> =
        colorUseCase.getHabitsColor()

    fun readAlarmChip(): LiveData<List<ChipAlarm>> =
        todoUseCase.readChipTime()

    fun insertTodoLocal(data : TodoLocal) =
        todoUseCase.insertTodoList(data)

    fun insertAlarmChip(alarm : ChipAlarm) =
        todoUseCase.insertChipTime(alarm)

    fun insertSubtask(data : TodoSubTask) =
        todoUseCase.insertSubtask(data)




}