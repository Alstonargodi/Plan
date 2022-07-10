package com.example.wetterbericht.domain

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.model.local.entity.habits.HabitsLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.model.local.entity.todolist.TodoandSubTask
import com.example.wetterbericht.model.repository.localrepository.LocalRepository
import java.time.LocalDateTime

//where business logic
class LocalInteractor(private val repository: ILocalRepository): LocalUseCase {
    private val currentDate = LocalDateTime.now().dayOfMonth

    override fun getOnBoardingStatus(): LiveData<Boolean> {
        return repository.getOnBoardingStatus()
    }

    override suspend fun saveOnBoardingStatus(onBoard: Boolean) {
        return repository.saveOnBoardingStatus(onBoard)
    }

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
       return repository.readChipTime()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        return repository.insertChipTime(alarm)
    }

    override fun readTodoSubtask(name: String): LiveData<List<TodoandSubTask>> {
        return repository.readTodoSubtask(name)
    }

    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return repository.readTodoLocal()
    }

    override fun getTodayTask(): LiveData<List<TodoLocal>> {
        return repository.getTodayTask(currentDate)
    }

    override fun getUpComingTask(): LiveData<List<TodoLocal>> {
        return repository.getUpComingTask(currentDate)
    }

    override fun getPreviousTask(): LiveData<List<TodoLocal>> {
        return repository.getPreviousTask(currentDate)
    }

    override fun getTodayTaskReminder(): List<TodoLocal> {
       return repository.getTodayTaskReminder(currentDate)
    }

    override fun insertTodoList(data: TodoLocal) {
        repository.insertTodoList(data)
    }

    override fun insertSubtask(data: TodoSubTask) {
        repository.insertSubtask(data)
    }

    override fun deleteTodoList(name: String) {
        repository.deleteTodoList(name)
    }

    override fun insertHabitsLocal(data: HabitsLocal) {
        repository.insertHabitsLocal(data)
    }
}