package com.example.wetterbericht.data.repository.local.habits

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.entities.dailyhabits.ColorHabits
import com.example.wetterbericht.data.local.entities.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entities.dailyhabits.IconHabits
import com.example.wetterbericht.data.local.source.habits.IHabitsLocalDataSource

class HabitsLocalRepository(
    private val dataSource: IHabitsLocalDataSource
): IHabitsLocalRepository {
    override fun getHabits(query: SupportSQLiteQuery): DataSource.Factory<Int, DailyHabits> {
        return dataSource.getHabits(query)
    }

    override fun readHabitsLocal(): LiveData<List<DailyHabits>> {
        return dataSource.readHabitsLocal()
    }

    override fun insertHabitsLocal(data: DailyHabits) {
        dataSource.insertHabitsLocal(data)
    }

    override fun deleteHabitsLocal(name: String) {
        dataSource.deleteHabitsLocal(name)
    }

    override fun getHabitsIcons(): LiveData<List<IconHabits>> {
       return dataSource.getHabitsIcon()
    }

    override fun getHabitsColors(): LiveData<List<ColorHabits>> {
        return dataSource.getHabitsColors()
    }

}