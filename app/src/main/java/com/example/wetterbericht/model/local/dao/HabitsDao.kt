package com.example.wetterbericht.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wetterbericht.model.local.entity.HabitsLocal

@Dao
abstract class HabitsDao {

    @Query("select*from habitstable")
    abstract fun readHabits(): LiveData<List<HabitsLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertHabits(data : HabitsLocal)

    @Query("delete from habitstable where name like :name")
    abstract fun deleteHabits(name : String)
}