package com.example.wetterbericht.viewmodel.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wetterbericht.model.room.todo
import com.example.wetterbericht.model.room.todoDatabase
import com.example.wetterbericht.model.repo.room.todoRepo
import com.example.wetterbericht.model.room.subtask
import com.example.wetterbericht.model.room.todoandsubtask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class todoviewmodel(application: Application): AndroidViewModel(application) {
    private val repo : todoRepo
    val readdata : LiveData<List<todoandsubtask>>

    init {
        val mdao = todoDatabase.setdatabase(application).todoDao()
        repo = todoRepo(mdao)
        readdata = repo.readdata
    }

    fun add(todo: todo){
        viewModelScope.launch(Dispatchers.IO) {
            repo.adddata(todo)
        }
    }

    fun addsub(subtask: ArrayList<subtask>){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addsub(subtask)
        }
    }


    fun update(todo: todo){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updatedata(todo)
        }
    }

    fun delete(todo: todo){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deletedata(todo)
        }
    }

    fun deleteall(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteall()
        }
    }
}