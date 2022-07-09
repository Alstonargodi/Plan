package com.example.wetterbericht.model.local

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask

interface ILocalDataSource {
    fun readChipTime():LiveData<List<ChipAlarm>>
    fun insertChipTime(alarm: ChipAlarm)

    fun readTodoLocal(): LiveData<List<TodoLocal>>
    fun getTodayTask(date : Int): LiveData<List<TodoLocal>>
    fun insertTodoList(data : TodoLocal)
    fun insertSubtask(data : TodoSubTask)
}