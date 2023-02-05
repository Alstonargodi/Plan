package com.example.wetterbericht.domain.localusecase.todotask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entity.dailytask.TodoandSubTask
import com.example.wetterbericht.data.repository.local.todo.ITodoLocalRepository
import com.example.wetterbericht.data.repository.local.todo.TodoLocalRepository
import com.example.wetterbericht.helpers.sortfilter.SortUtils
import com.example.wetterbericht.helpers.sortfilter.TodoSortType
import java.time.LocalDateTime

class TodoLocalInteractor(
    private val repository : ITodoLocalRepository
): TodoLocalUseCase {
    private val currentDate = LocalDateTime.now().dayOfMonth

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
        return repository.readChipTime()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        return repository.insertChipTime(alarm)
    }

    override fun readNearestActiveTask(): TodoLocal {
        return repository.readNearestActiveTask()
    }

    override fun readTodoTaskFilter(query: TodoSortType): LiveData<PagedList<TodoLocal>> {
        val todoQuery = SortUtils.getFilterQueryTodo(query)
        val habits = repository.readTodoTaskFilter(todoQuery)
        val pagedConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(20)
            .build()
        return LivePagedListBuilder(habits,pagedConfig).build()
    }

    override fun readTodoSubtask(name: String): LiveData<List<TodoandSubTask>> {
        return repository.readTodoSubtask(name)
    }

    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return repository.readTodoLocal()
    }

    override fun getTodayTask(): LiveData<List<TodoLocal>> {
        Log.d("todolocalinteractor",currentDate.toString())
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

    override fun updateTaskStatus(id: Int, status: Boolean) {
        repository.updateTaskStatus(id, status)
    }

    override fun updateSubtask(id: Int, status: Boolean) {
        repository.updateSubtask(id, status)
    }
}