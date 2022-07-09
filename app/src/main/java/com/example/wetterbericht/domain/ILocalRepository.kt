package com.example.wetterbericht.domain

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask

interface ILocalRepository {
    fun readChipTime():LiveData<List<ChipAlarm>>
    fun insertChipTime(alarm: ChipAlarm)
    fun readTodoLocal(): LiveData<List<TodoLocal>>
    fun getTodayTask(date : Int): LiveData<List<TodoLocal>>
    fun insertTodoList(data : TodoLocal)
    fun insertSubtask(data : TodoSubTask)
}