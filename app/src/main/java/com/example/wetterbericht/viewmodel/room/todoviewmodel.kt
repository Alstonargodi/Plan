package com.example.wetterbericht.viewmodel.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wetterbericht.model.room.Do.todoDatabase
import com.example.wetterbericht.model.repo.room.todoRepo
import com.example.wetterbericht.model.room.*
import com.example.wetterbericht.model.room.Do.Outside
import com.example.wetterbericht.model.room.Do.outsideandsubtask
import com.example.wetterbericht.model.room.Do.subtaskoutside
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class todoviewmodel(application: Application): AndroidViewModel(application) {
    private val repo : todoRepo
    val readinside : LiveData<List<insidendsubtask>>
    val readoutside : LiveData<List<outsideandsubtask>>
    val readsubtaskin : LiveData<List<subtaskinside>>

    init {
        val mdao = todoDatabase.setdatabase(application).todoDao()

        repo = todoRepo(mdao)
        readinside = repo.readinside
        readoutside = repo.readoutside
        readsubtaskin = repo.readsubtask

    }

    //main
    fun addinside(todo: Inside){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addinside(todo)
        }
    }
    fun addoutside(outside: Outside){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addoutside(outside)
        }
    }

    //subtask
    fun addsubinside(subtask: ArrayList<subtaskinside>){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addsubinside(subtask)
        }
    }
    fun addsuboutside(subtaskoutside: ArrayList<subtaskoutside>){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addsuboutside(subtaskoutside)
        }
    }

    fun selectsub(name : String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.selectsub(name)
        }
    }

    fun update(todo: Inside){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updatedata(todo)
        }
    }

    fun delete(todo: Inside){
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