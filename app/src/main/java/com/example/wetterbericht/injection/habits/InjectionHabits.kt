package com.example.wetterbericht.injection.habits

import android.content.Context
import com.example.wetterbericht.data.local.source.habits.HabitsLocalDataSource
import com.example.wetterbericht.data.local.source.habits.IHabitsLocalDataSource
import com.example.wetterbericht.data.repository.local.habits.HabitsLocalRepository
import com.example.wetterbericht.data.repository.local.habits.IHabitsLocalRepository
import com.example.wetterbericht.domain.localusecase.habits.HabitsLocalInteractor
import com.example.wetterbericht.domain.localusecase.habits.HabitsLocalUseCase

object InjectionHabits {
    fun provideHabitsUseCase(context: Context): HabitsLocalUseCase {
        return HabitsLocalInteractor(provideHabitsRepository(context))
    }

    private fun provideHabitsRepository(context: Context): IHabitsLocalRepository {
        return HabitsLocalRepository(provideHabitsDatasource(context))
    }

    private fun provideHabitsDatasource(context: Context): IHabitsLocalDataSource {
        return HabitsLocalDataSource(context)
    }
}