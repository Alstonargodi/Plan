package com.example.wetterbericht.data.repository.local.habits

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.entity.dailyhabits.ColorHabits
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailyhabits.IconHabits

interface IHabitsLocalRepository {
    //habits
    fun getHabits(query: SupportSQLiteQuery): DataSource.Factory<Int, DailyHabits>
    fun readHabitsLocal(): LiveData<List<DailyHabits>>
    fun insertHabitsLocal(data: DailyHabits)
    fun deleteHabitsLocal(name: String)
    //icon
    fun getHabitsIcons(): LiveData<List<IconHabits>>
    //color
    fun getHabitsColors() : LiveData<List<ColorHabits>>

}