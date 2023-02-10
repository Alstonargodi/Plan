package com.example.wetterbericht.data.local.dao.dailyhabits

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.entities.dailyhabits.DailyHabits
import androidx.paging.DataSource
import androidx.room.*
import com.example.wetterbericht.data.local.entities.dailyhabits.ColorHabits
import com.example.wetterbericht.data.local.entities.dailyhabits.IconHabits

@Dao
abstract class DailyHabitsDao {
    @RawQuery(observedEntities = [DailyHabits::class])
    abstract fun getHabits(query: SupportSQLiteQuery): DataSource.Factory<Int,DailyHabits>

    @Query("select*from habitstable")
    abstract fun readHabits(): LiveData<List<DailyHabits>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertHabits(data : DailyHabits)

    @Query("delete from habitstable where title like :name")
    abstract fun deleteHabits(name : String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertHabitsIcon(data : IconHabits)

    @Query("select * from habitsicon")
    abstract fun readHabitsIcon(): LiveData<List<IconHabits>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertColorHabits(data : ColorHabits)

    @Query("select * from habitscolor")
    abstract fun readHabitsColor(): LiveData<List<ColorHabits>>


}