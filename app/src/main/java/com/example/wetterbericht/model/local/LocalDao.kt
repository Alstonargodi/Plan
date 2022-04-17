package com.example.wetterbericht.model.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class LocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWeather(data : WeatherLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTodo(data : TodoLocal)


    @Query("select*from WeatherTable")
    abstract fun readWeather() : LiveData<List<WeatherLocal>>

    @Query("select*from TodoTable")
    abstract fun readTodo() : LiveData<List<TodoLocal>>


    @Query("delete from WeatherTable where loc like :name")
    abstract fun deleteWeather(name : String)

    @Query("delete from TodoTable where title like :name ")
    abstract fun deleteTodo(name : String)



}