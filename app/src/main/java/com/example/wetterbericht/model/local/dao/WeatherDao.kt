package com.example.wetterbericht.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wetterbericht.model.local.entity.WeatherLocal

@Dao
abstract class WeatherDao {

    @Query("select*from weathertable")
    abstract fun readWeather() : LiveData<List<WeatherLocal>>

    @Query("delete from weathertable where loc like :name")
    abstract fun deleteWeather(name : String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWeather(data : WeatherLocal)

    @Query("select*from weathertable where loc like :name ")
    abstract fun searchLocation(name: String): LiveData<List<WeatherLocal>>

    @Query("select*from weathertable")
    abstract fun getWeatherLocationName() : WeatherLocal
}