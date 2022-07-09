package com.example.wetterbericht.domain

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.model.repository.localrepository.LocalRepository
import java.time.LocalDateTime

class LocalInteractor(private val repository: ILocalRepository): LocalUseCase {
    private val currentDate = LocalDateTime.now().dayOfMonth

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
       return repository.readChipTime()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        return repository.insertChipTime(alarm)
    }

    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return repository.readTodoLocal()
    }

    override fun getTodayTask(): LiveData<List<TodoLocal>> {
        return repository.getTodayTask(currentDate)
    }

    override fun insertTodoList(data: TodoLocal) {
        repository.insertTodoList(data)
    }

    override fun insertSubtask(data: TodoSubTask) {
        repository.insertSubtask(data)
    }
}