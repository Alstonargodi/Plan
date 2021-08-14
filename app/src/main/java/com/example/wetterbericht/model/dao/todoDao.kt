package com.example.wetterbericht.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wetterbericht.model.room.todo

@Dao
abstract class todoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun adddata(todo: todo)

    @Query("select*from tabeltodo order by id")
    abstract fun readdata() : LiveData<List<todo>>

    @Delete
    abstract fun deletedata(todo: todo)
}