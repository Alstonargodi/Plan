package com.example.wetterbericht.data.local.source.todotask

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.database.RoomDatabaseConfig
import com.example.wetterbericht.data.local.entities.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entities.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entities.dailytask.TodoandSubTask
import java.util.concurrent.Executors

class TodoLocalDataSource(
    val context : Context
): ITodoLocalDataSource {
    private val todoDao = RoomDatabaseConfig.setInstance(context).todoDao()
    private val executorService = Executors.newSingleThreadExecutor()

    override fun readNearestActiveTask(): TodoLocal {
        return todoDao.readNearestActiveTask()
    }

    override fun readTodoTaskFilter(query: SupportSQLiteQuery)
    : DataSource.Factory<Int, TodoLocal> {
        return todoDao.readTodoTaskFilter(query)
    }

    override fun readTodoSubtask(name: String): LiveData<List<TodoandSubTask>> {
        return todoDao.readSubTaskTodoList(name)
    }

    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return todoDao.readTodoList()
    }

    override fun getTodayTask(
        date: Int,
        month : Int,
        year : Int
    ): LiveData<List<TodoLocal>> {
        return todoDao.readTodayTask(date, month, year)
    }

    override fun getUpComingTask(
        date: Int,
        month : Int,
        year : Int
    ): LiveData<List<TodoLocal>> {
        return todoDao.readUpcomingTask(date, month, year)
    }

    override fun getPreviousTask(
        date: Int,
        month : Int,
        year : Int
    ): LiveData<List<TodoLocal>> {
        return todoDao.readPreviousTask(date,year,month)
    }

    override fun getTodayTaskReminder(date : Int): List<TodoLocal> {
        return todoDao.readTodayTaskReminder(date)
    }

    override fun insertTodoList(data: TodoLocal) {
        executorService.execute { todoDao.insertTodoList(data) }
    }

    override fun insertSubtask(data: TodoSubTask) {
        executorService.execute { todoDao.insertSubTask(data) }
    }

    override fun deleteTodoList(name: String) {
        executorService.execute { todoDao.deleteTodoTask(name) }
    }

    override fun updateTaskStatus(id: Int, status: Boolean) {
        executorService.execute { todoDao.updateTaskStatus(id, status) }
    }

    override fun updateSubtask(id: Int, status: Boolean) {
        executorService.execute { todoDao.updateSubTaskStatus(id,status) }
    }

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
        return todoDao.readTimeChip()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        executorService.execute { todoDao.insertTimeChip(alarm) }
    }
}