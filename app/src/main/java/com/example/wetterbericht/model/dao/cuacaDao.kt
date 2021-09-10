package com.example.wetterbericht.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wetterbericht.model.room.cuaca

@Dao
abstract class cuacaDao {
    //add
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun adddata(data : cuaca)

    //read
    @Query("select*from tabelcuaca order by id")
    abstract fun readdata() : LiveData<List<cuaca>>

    @Query("delete from tabelcuaca")
    abstract fun deletedata()

    @Update
    abstract fun update(data: cuaca)

}