package com.example.wetterbericht.domain.localusecase.habits

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.wetterbericht.data.local.entities.dailyhabits.ColorHabits
import com.example.wetterbericht.data.local.entities.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entities.dailyhabits.IconHabits
import com.example.wetterbericht.helpers.sortfilter.HabitSortType

interface HabitsLocalUseCase {
    //habits
    fun getHabits(filter : HabitSortType): LiveData<PagedList<DailyHabits>>
    fun readHabitsLocal(): LiveData<List<DailyHabits>>
    fun insertHabitsLocal(data: DailyHabits)
    fun deleteHabitsLocal(name: String)
    //icon
    fun getHabitsIcon(): LiveData<List<IconHabits>>
    //color
    fun getHabitsColor(): LiveData<List<ColorHabits>>
}