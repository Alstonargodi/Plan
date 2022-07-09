package com.example.wetterbericht.domain

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal

interface LocalUseCase {
    fun readTodoLocal(): LiveData<List<TodoLocal>>
}