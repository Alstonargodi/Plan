package com.example.wetterbericht.viewmodel.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wetterbericht.model.room.todo
import com.example.wetterbericht.model.room.todoDatabase
import com.example.wetterbericht.repo.room.todoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class todoviewmodel(application: Application): AndroidViewModel(application) {
    private val repo : todoRepo
    val readdata : LiveData<List<todo>>

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
}