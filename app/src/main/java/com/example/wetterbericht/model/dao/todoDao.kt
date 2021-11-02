package com.example.wetterbericht.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wetterbericht.model.room.subtask
import com.example.wetterbericht.model.room.todo
import com.example.wetterbericht.model.room.todoandsubtask

@Dao
abstract class todoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun adddata(todo: todo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addsub(subtask: ArrayList<subtask>)

    @Transaction
    @Query("select*from tabeltodo")
    abstract fun readdata() : LiveData<List<todoandsubtask>>

    @Delete
    abstract fun deletedata(todo: todo)

    @Update
    abstract fun updatedata(todo: todo)

    @Query("delete from tabeltodo")
    abstract fun destroytodo()

}