package com.example.wetterbericht.model.repo.room

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.dao.todoDao
import com.example.wetterbericht.model.room.subtask
import com.example.wetterbericht.model.room.todo
import com.example.wetterbericht.model.room.todoandsubtask

class todoRepo(val dao: todoDao) {
    val readdata : LiveData<List<todoandsubtask>> = dao.readdata()

    fun adddata(todo :todo){
        dao.adddata(todo)
    }

    fun addsub(subtask: ArrayList<subtask>){
        dao.addsub(subtask)
    }


    fun updatedata(todo: todo){
        dao.updatedata(todo)
    }

    fun deletedata(todo: todo){
        dao.deletedata(todo)
    }

    fun deleteall(){
        dao.destroytodo()
    }
}