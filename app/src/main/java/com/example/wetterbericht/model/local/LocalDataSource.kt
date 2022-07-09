package com.example.wetterbericht.model.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.dao.todolist.TodoDao
import com.example.wetterbericht.model.local.database.LocalDatabase
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask
import java.time.LocalDateTime
import java.util.concurrent.Executors

class LocalDataSource(val context: Context) : ILocalDataSource {
    val database = LocalDatabase.setDatabase(context).todoDao()
    private val executorService = Executors.newSingleThreadExecutor()

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
       return database.readAlarmChip()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        executorService.execute { database.insertAlarmChip(alarm) }
    }

    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return database.readTodo()
    }

    override fun getTodayTask(date : Int): LiveData<List<TodoLocal>> {
       return database.getTodayTask(date)
    }

    override fun insertTodoList(data: TodoLocal) {
        executorService.execute { database.insertTodo(data) }
    }

    override fun insertSubtask(data: TodoSubTask) {
        executorService.execute { database.insertSubTask(data) }
    }


}