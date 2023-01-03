package com.example.wetterbericht.data.local.source.todotask

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.database.LocalDatabase
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entity.dailytask.TodoandSubTask
import java.util.concurrent.Executors

class TodoLocalDataSource(
    val context : Context
): ITodoLocalDataSource {
    private val todoDao = LocalDatabase.setInstance(context).todoDao()
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

    override fun getTodayTask(date : Int): LiveData<List<TodoLocal>> {
        return todoDao.readTodayTask(date)
    }

    override fun getUpComingTask(date: Int): LiveData<List<TodoLocal>> {
        return todoDao.readUpcomingTask(date)
    }

    override fun getPreviousTask(date: Int): LiveData<List<TodoLocal>> {
        return todoDao.readPreviousTask(date)
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

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
        return todoDao.readTimeChip()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        executorService.execute { todoDao.insertTimeChip(alarm) }
    }
}