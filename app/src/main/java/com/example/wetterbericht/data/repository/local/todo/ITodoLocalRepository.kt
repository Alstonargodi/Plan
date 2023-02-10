package com.example.wetterbericht.data.repository.local.todo

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entities.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entities.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entities.dailytask.TodoandSubTask

interface ITodoLocalRepository {
    //todolist
    fun readNearestActiveTask(): TodoLocal
    fun readTodoTaskFilter(query: SupportSQLiteQuery): DataSource.Factory<Int, TodoLocal>
    fun readTodoLocal(): LiveData<List<TodoLocal>>
    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>>
    fun getTodayTaskReminder(date : Int): List<TodoLocal>
    fun getTodayTask(date: Int,month : Int,year : Int): LiveData<List<TodoLocal>>
    fun getUpComingTask(date: Int,month : Int,year : Int): LiveData<List<TodoLocal>>
    fun getPreviousTask(date: Int,month : Int,year : Int): LiveData<List<TodoLocal>>
    fun insertTodoList(data : TodoLocal)
    fun insertSubtask(data : TodoSubTask)
    fun deleteTodoList(name : String)
    fun updateTaskStatus(id : Int,status : Boolean)
    fun updateSubtask(id : Int,status : Boolean)
    //chipTime
    fun readChipTime():LiveData<List<ChipAlarm>>
    fun insertChipTime(alarm: ChipAlarm)

}