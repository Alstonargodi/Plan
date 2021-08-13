package com.example.wetterbericht.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wetterbericht.model.room.cuaca

@Dao
abstract class cuacaDao {
    //add
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun adddata(data : cuaca)

    //read
    @Query("select*from tabelcuaca order by id")
    abstract fun readdata() : LiveData<cuaca>

    @Delete
    abstract fun deletedata(data: cuaca)

}