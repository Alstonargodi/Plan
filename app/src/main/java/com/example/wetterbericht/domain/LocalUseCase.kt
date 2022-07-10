package com.example.wetterbericht.domain

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.model.local.entity.habits.HabitsLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.model.local.entity.todolist.TodoandSubTask

interface LocalUseCase{
    //onboarding
    fun getOnBoardingStatus(): LiveData<Boolean>
    suspend fun saveOnBoardingStatus(onBoard : Boolean)
    //time chip
    fun readChipTime():LiveData<List<ChipAlarm>>
    fun insertChipTime(alarm: ChipAlarm)
    //todolist
    fun insertTodoList(data : TodoLocal)
    fun insertSubtask(data : TodoSubTask)
    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>>
    fun readTodoLocal(): LiveData<List<TodoLocal>>
    fun getTodayTaskReminder(): List<TodoLocal>
    fun getTodayTask(): LiveData<List<TodoLocal>>
    fun getUpComingTask(): LiveData<List<TodoLocal>>
    fun getPreviousTask(): LiveData<List<TodoLocal>>
    fun deleteTodoList(name : String)
    //habits
    fun insertHabitsLocal(data: HabitsLocal)

}