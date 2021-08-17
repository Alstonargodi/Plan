package com.example.wetterbericht.viewmodel.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wetterbericht.model.room.cuaca
import com.example.wetterbericht.model.room.cuacaDatabase
import com.example.wetterbericht.repo.room.CuacaRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Cuacaviewmodel(application: Application): AndroidViewModel(application) {
        private val repo : CuacaRepo
        //read
        val readdata : LiveData<List<cuaca>>

        init {
            val mdao = cuacaDatabase.setdatabase(application).cuacaDao()
            repo = CuacaRepo(mdao)
            readdata = repo.readdata
        }

        fun add(data : cuaca){
            viewModelScope.launch(Dispatchers.IO) {
                repo.addata(data)
            }
        }
}