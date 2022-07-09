package com.example.wetterbericht.model.local

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal

interface ILocalDataSource {
    fun readTodoLocal(): LiveData<List<TodoLocal>>
}