package com.example.wetterbericht.data.local.source.habits

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailyhabits.IconHabits

interface IHabitsLocalDataSource {
    //habits
    fun getHabits(query: SupportSQLiteQuery): DataSource.Factory<Int, DailyHabits>
    fun readHabitsLocal(): LiveData<List<DailyHabits>>
    fun insertHabitsLocal(data: DailyHabits)
    fun deleteHabitsLocal(name: String)
    fun getHabitsIcon(): LiveData<List<IconHabits>>
}