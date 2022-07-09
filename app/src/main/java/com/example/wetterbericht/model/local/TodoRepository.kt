package com.example.wetterbericht.model.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.wetterbericht.domain.ILocalRepository
import com.example.wetterbericht.domain.LocalUseCase
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask

class TodoRepository(
    private val dataSource: ILocalDataSource
):ILocalRepository {

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
        return dataSource.readChipTime()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        dataSource.insertChipTime(alarm)
    }

    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return dataSource.readTodoLocal()
    }

    override fun getTodayTask(date : Int): LiveData<List<TodoLocal>> {
        return dataSource.getTodayTask(date)
    }

    override fun insertTodoList(data: TodoLocal) {
        dataSource.insertTodoList(data)
    }

    override fun insertSubtask(data: TodoSubTask) {
        dataSource.insertSubtask(data)
    }

}