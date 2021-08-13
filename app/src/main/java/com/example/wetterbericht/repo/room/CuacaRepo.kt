package com.example.wetterbericht.repo.room

import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.dao.cuacaDao
import com.example.wetterbericht.model.room.cuaca

class CuacaRepo(val dao: cuacaDao) {
    val readdata : LiveData<cuaca> = dao.readdata()

    fun addata(cur : cuaca){
        dao.adddata(cur)
    }

}