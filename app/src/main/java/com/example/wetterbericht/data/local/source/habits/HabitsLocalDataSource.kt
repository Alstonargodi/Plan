package com.example.wetterbericht.data.local.source.habits

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.database.LocalDatabase
import com.example.wetterbericht.data.local.entity.dailyhabits.ColorHabits
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailyhabits.IconHabits
import java.util.concurrent.Executors

class HabitsLocalDataSource(val context: Context): IHabitsLocalDataSource {
    private val habitsDao = LocalDatabase.setInstance(context).habitsDao()
    private val executorService = Executors.newSingleThreadExecutor()

    override fun getHabits(query: SupportSQLiteQuery): DataSource.Factory<Int, DailyHabits> {
        return habitsDao.getHabits(query)
    }

    override fun readHabitsLocal(): LiveData<List<DailyHabits>> {
        return habitsDao.readHabits()
    }

    override fun insertHabitsLocal(data: DailyHabits) {
        executorService.execute { habitsDao.insertHabits(data) }
    }

    override fun deleteHabitsLocal(name: String) {
        habitsDao.deleteHabits(name)
    }

    override fun getHabitsIcon(): LiveData<List<IconHabits>> {
        return habitsDao.readHabitsIcon()
    }

    override fun getHabitsColors(): LiveData<List<ColorHabits>> {
        return habitsDao.readHabitsColor()
    }

}