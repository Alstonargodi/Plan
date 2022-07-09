package com.example.wetterbericht.model.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.dao.todolist.TodoDao
import com.example.wetterbericht.model.local.database.LocalDatabase
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal

class LocalDataSource(val context: Context) : ILocalDataSource {
    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return LocalDatabase
            .setDatabase(context).todoDao().readTodo()
    }
}