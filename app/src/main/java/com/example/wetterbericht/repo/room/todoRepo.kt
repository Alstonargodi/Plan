package com.example.wetterbericht.repo.room

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.dao.todoDao
import com.example.wetterbericht.model.room.todo

class todoRepo(val dao: todoDao) {
    val readdata : LiveData<todo> = dao.readdata()

    fun adddata(todo :todo){
        dao.adddata(todo)
    }
}