package com.example.wetterbericht.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wetterbericht.model.local.*
import com.example.wetterbericht.model.local.entity.WeatherLocal

@Dao
abstract class TodoDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTodo(data : TodoLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSubTask(sub : TodoSubTask)


    @Query("select*from TodoTable")
    abstract fun readTodo() : LiveData<List<TodoLocal>>


    @Query("select*from TodoTable where title = :name")
    abstract fun readSearchTodo(name: String): LiveData<List<TodoLocal>>

    @Transaction
    @Query("select * from TodoTable where title = :name")
    abstract fun getTodoSubtask(name : String): LiveData<List<TodoandSubTask>>

    @Query("delete from TodoTable where title like :name ")
    abstract fun deleteTodo(name : String)



    @Query("select*from WeatherTable")
    abstract fun readWeather() : LiveData<List<WeatherLocal>>

    @Query("delete from WeatherTable where loc like :name")
    abstract fun deleteWeather(name : String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWeather(data : WeatherLocal)

    @Query("select*from weathertable where loc like :name ")
    abstract fun searchLocation(name: String): LiveData<List<WeatherLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAlarmChip(alarm : ChipAlarm)

    @Query("select*from ChipAlarmTable")
    abstract fun readAlarmChip() : LiveData<List<ChipAlarm>>



}