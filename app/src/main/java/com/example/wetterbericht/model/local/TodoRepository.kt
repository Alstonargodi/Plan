package com.example.wetterbericht.model.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.wetterbericht.domain.ILocalRepository
import com.example.wetterbericht.domain.LocalUseCase
import com.example.wetterbericht.model.local.entity.habits.HabitsLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.model.local.entity.todolist.TodoandSubTask

class TodoRepository(
    private val dataSource: ILocalDataSource
):ILocalRepository {

    override fun getOnBoardingStatus(): LiveData<Boolean> {
        return dataSource.getOnBoardingStatus()
    }

    override suspend fun saveOnBoardingStatus(onBoard: Boolean) {
       dataSource.saveOnBoardingStatus(onBoard)
    }

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
        return dataSource.readChipTime()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        dataSource.insertChipTime(alarm)
    }

    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return dataSource.readTodoLocal()
    }

    override fun readTodoSubtask(name: String): LiveData<List<TodoandSubTask>> {
        return dataSource.readTodoSubtask(name)
    }

    override fun getTodayTask(date : Int): LiveData<List<TodoLocal>> {
        return dataSource.getTodayTask(date)
    }

    override fun getUpComingTask(date: Int): LiveData<List<TodoLocal>> {
        return dataSource.getUpComingTask(date)
    }

    override fun getPreviousTask(date: Int): LiveData<List<TodoLocal>> {
        return dataSource.getPreviousTask(date)
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

    override fun insertHabitsLocal(data: HabitsLocal) {
        dataSource.insertHabitsLocal(data)
    }

}