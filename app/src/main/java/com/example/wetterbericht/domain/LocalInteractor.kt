package com.example.wetterbericht.domain

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.repository.localrepository.LocalRepository

class LocalInteractor(private val repository: ILocalRepository): LocalUseCase {
    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return repository.readTodoLocal()
    }
}