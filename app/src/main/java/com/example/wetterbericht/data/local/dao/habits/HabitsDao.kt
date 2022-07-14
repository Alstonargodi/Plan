package com.example.wetterbericht.data.local.dao.habits

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.entity.habits.HabitsLocal
import androidx.paging.DataSource
import androidx.room.*


@Dao
abstract class HabitsDao {

    @RawQuery(observedEntities = [HabitsLocal::class])
    abstract fun getHabits(query: SupportSQLiteQuery): DataSource.Factory<Int,HabitsLocal>

    @Query("select*from habitstable")
    abstract fun readHabits(): LiveData<List<HabitsLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertHabits(data : HabitsLocal)

    @Query("delete from habitstable where title like :name")
    abstract fun deleteHabits(name : String)
}