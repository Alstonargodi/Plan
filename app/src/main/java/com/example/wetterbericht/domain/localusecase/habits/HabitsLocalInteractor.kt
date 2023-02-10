package com.example.wetterbericht.domain.localusecase.habits

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.wetterbericht.data.local.entities.dailyhabits.ColorHabits
import com.example.wetterbericht.data.local.entities.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entities.dailyhabits.IconHabits
import com.example.wetterbericht.data.repository.local.habits.IHabitsLocalRepository
import com.example.wetterbericht.helpers.sortfilter.HabitSortType
import com.example.wetterbericht.helpers.sortfilter.SortUtils

class HabitsLocalInteractor(
    private val repository : IHabitsLocalRepository
): HabitsLocalUseCase{
    override fun getHabits(filter : HabitSortType): LiveData<PagedList<DailyHabits>> {
        val query = SortUtils.getSortedQueryHabits(filter)
        val habits = repository.getHabits(query)

        val pagedConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(5)
            .build()

        return LivePagedListBuilder(habits,pagedConfig).build()
    }

    override fun readHabitsLocal(): LiveData<List<DailyHabits>> {
        return repository.readHabitsLocal()
    }

    override fun insertHabitsLocal(data: DailyHabits) {
        repository.insertHabitsLocal(data)
    }

    override fun deleteHabitsLocal(name: String) {
        repository.readHabitsLocal()
    }

    override fun getHabitsIcon(): LiveData<List<IconHabits>> {
        return repository.getHabitsIcons()
    }

    override fun getHabitsColor(): LiveData<List<ColorHabits>> {
        return repository.getHabitsColors()
    }
}