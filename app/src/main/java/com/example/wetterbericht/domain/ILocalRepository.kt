package com.example.wetterbericht.domain

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal

interface ILocalRepository {
    fun readTodoLocal(): LiveData<List<TodoLocal>>
}