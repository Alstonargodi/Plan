package com.example.wetterbericht.model.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.wetterbericht.domain.ILocalRepository
import com.example.wetterbericht.domain.LocalUseCase
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal

class TodoRepository(
    private val dataSource: ILocalDataSource
):ILocalRepository {
    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return dataSource.readTodoLocal()
    }
}