package com.example.wetterbericht.domain.localusecase.habits

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailyhabits.IconHabits
import com.example.wetterbericht.helpers.sortfilter.HabitSortType

interface HabitsLocalUseCase {
    //habits
    fun getHabits(filter : HabitSortType): LiveData<PagedList<DailyHabits>>
    fun readHabitsLocal(): LiveData<List<DailyHabits>>
    fun insertHabitsLocal(data: DailyHabits)
    fun deleteHabitsLocal(name: String)
    fun getHabitsIcon(): LiveData<List<IconHabits>>
}