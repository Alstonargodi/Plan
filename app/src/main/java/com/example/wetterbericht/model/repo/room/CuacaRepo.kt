package com.example.wetterbericht.model.repo.room

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.dao.cuacaDao
import com.example.wetterbericht.model.room.cuaca

class CuacaRepo(val dao: cuacaDao) {
    val readdata : LiveData<List<cuaca>> = dao.readdata()

    fun addata(cur : cuaca){
        dao.adddata(cur)
    }

    fun updatedata(cur : cuaca){
        dao.update(cur)
    }

    fun deleteallcuaca(){
        dao.deletedata()
    }

}