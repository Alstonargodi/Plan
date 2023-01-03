package com.example.wetterbericht.presentation.fragment.habits.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import com.example.wetterbericht.data.local.entity.dailyhabits.ColorHabits
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailyhabits.IconHabits
import com.example.wetterbericht.domain.localusecase.habits.HabitsLocalUseCase
import com.example.wetterbericht.helpers.sortfilter.HabitSortType
import com.example.wetterbericht.helpers.sortfilter.TodoSortType

class HabitsViewModel(
    private val useCase: HabitsLocalUseCase
): ViewModel(){
    private val habitsFilter = MutableLiveData<HabitSortType>()

    init {
        habitsFilter.value = HabitSortType.START_TIME
    }

    //habits
    fun getHabits(): LiveData<PagedList<DailyHabits>> =
        habitsFilter.switchMap { useCase.getHabits(it) }

    fun readHabits(): LiveData<List<DailyHabits>> =
        useCase.readHabitsLocal()

    fun insertHabits(data : DailyHabits) =
        useCase.insertHabitsLocal(data)

    fun deleteHabits(name : String)=
        useCase.deleteHabitsLocal(name)

    fun filter(filter : HabitSortType){
        habitsFilter.value = filter
    }

    fun readHabitsIcon(): LiveData<List<IconHabits>> =
        useCase.getHabitsIcon()

    fun readHabitsColors(): LiveData<List<ColorHabits>> =
        useCase.getHabitsColor()
}