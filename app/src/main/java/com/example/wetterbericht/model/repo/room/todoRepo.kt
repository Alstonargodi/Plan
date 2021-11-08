package com.example.wetterbericht.model.repo.room

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.dao.todoDao
import com.example.wetterbericht.model.room.*
import com.example.wetterbericht.model.room.Do.Outside
import com.example.wetterbericht.model.room.Do.outsideandsubtask
import com.example.wetterbericht.model.room.Do.subtaskoutside

class todoRepo(val dao: todoDao) {
    val name : String = ""
    val readinside : LiveData<List<insidendsubtask>> = dao.readinside()
    val readoutside : LiveData<List<outsideandsubtask>> = dao.readoutside()
    val readsubtask : LiveData<List<subtaskinside>> = dao.selectsubtask("tugas0")

    fun addinside(todo :Inside){
        dao.adddatainside(todo)
    }

    fun addoutside(todo :Outside){
        dao.addoutside(todo)
    }

    fun addsubinside(subtask: ArrayList<subtaskinside>){
        dao.addsubinside(subtask)
    }

    fun addsuboutside(subtask: ArrayList<subtaskoutside>){
        dao.addsuboutside(subtask)
    }

    fun selectsub(name : String){
        dao.selectsubtask(name)
    }

    fun updatedata(todo: Inside){
        dao.updatedata(todo)
    }

    fun deletedata(todo: Inside){
        dao.deletedata(todo)
    }

    fun deleteall(){
        dao.destroytodo()
    }
}