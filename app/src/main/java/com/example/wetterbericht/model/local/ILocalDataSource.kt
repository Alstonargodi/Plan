package com.example.wetterbericht.model.local

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.entity.habits.HabitsLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.model.local.entity.todolist.TodoandSubTask

interface ILocalDataSource {
    //onboarding
    fun getOnBoardingStatus(): LiveData<Boolean>
    suspend fun saveOnBoardingStatus(onBoard : Boolean)
    //chiptime
    fun readChipTime():LiveData<List<ChipAlarm>>
    fun insertChipTime(alarm: ChipAlarm)
    //todolist
    fun insertTodoList(data : TodoLocal)
    fun insertSubtask(data : TodoSubTask)
    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>>
    fun readTodoLocal(): LiveData<List<TodoLocal>>
    fun getTodayTaskReminder(date : Int): List<TodoLocal>
    fun getTodayTask(date : Int): LiveData<List<TodoLocal>>
    fun getUpComingTask(date : Int): LiveData<List<TodoLocal>>
    fun getPreviousTask(date: Int): LiveData<List<TodoLocal>>
    fun deleteTodoList(name : String)
    //habits
    fun insertHabitsLocal(data: HabitsLocal)
}