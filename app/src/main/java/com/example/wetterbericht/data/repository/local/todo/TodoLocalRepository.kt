package com.example.wetterbericht.data.repository.local.todo

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entities.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entities.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entities.dailytask.TodoandSubTask
import com.example.wetterbericht.data.local.source.todotask.ITodoLocalDataSource

class TodoLocalRepository(
    private val dataSource: ITodoLocalDataSource
):ITodoLocalRepository {

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
        return dataSource.readChipTime()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        dataSource.insertChipTime(alarm)
    }

    override fun readNearestActiveTask(): TodoLocal {
        return dataSource.readNearestActiveTask()
    }

    override fun readTodoTaskFilter(query: SupportSQLiteQuery)
    : DataSource.Factory<Int, TodoLocal> {
        return dataSource.readTodoTaskFilter(query)
    }

    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return dataSource.readTodoLocal()
    }

    override fun readTodoSubtask(name: String): LiveData<List<TodoandSubTask>> {
        return dataSource.readTodoSubtask(name)
    }

    override fun getTodayTask(
        date: Int,
        month : Int,
        year : Int
    ): LiveData<List<TodoLocal>> {
        return dataSource.getTodayTask(date, month, year)
    }

    override fun getUpComingTask(
        date: Int,
        month : Int,
        year : Int
    ): LiveData<List<TodoLocal>> {
        return dataSource.getUpComingTask(date,month,year)
    }

    override fun getPreviousTask(
        date: Int,
        month : Int,
        year : Int
    ): LiveData<List<TodoLocal>> {
        return dataSource.getPreviousTask(date,month, year)
    }

    override fun getTodayTaskReminder(date: Int): List<TodoLocal> {
        return dataSource.getTodayTaskReminder(date)
    }

    override fun insertTodoList(data: TodoLocal) {
        dataSource.insertTodoList(data)
    }

    override fun insertSubtask(data: TodoSubTask) {
        dataSource.insertSubtask(data)
    }

    override fun deleteTodoList(name: String) {
        dataSource.deleteTodoList(name)
    }

    override fun updateTaskStatus(id: Int, status: Boolean) {
        dataSource.updateTaskStatus(id, status)
    }

    override fun updateSubtask(id: Int, status: Boolean) {
        dataSource.updateSubtask(id, status)
    }
}