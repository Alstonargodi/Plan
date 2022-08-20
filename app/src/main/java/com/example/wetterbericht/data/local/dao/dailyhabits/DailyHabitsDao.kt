package com.example.wetterbericht.data.local.dao.dailyhabits

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import androidx.paging.DataSource
import androidx.room.*


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
}