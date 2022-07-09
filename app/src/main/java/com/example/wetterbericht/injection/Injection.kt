package com.example.wetterbericht.injection

import android.content.Context
import com.example.wetterbericht.domain.ILocalRepository
import com.example.wetterbericht.domain.LocalInteractor
import com.example.wetterbericht.domain.LocalUseCase
import com.example.wetterbericht.model.local.ILocalDataSource
import com.example.wetterbericht.model.local.LocalDataSource
import com.example.wetterbericht.model.local.TodoRepository
import com.example.wetterbericht.model.local.database.LocalDatabase
import com.example.wetterbericht.model.local.preferences.OnboardingPreferences
import com.example.wetterbericht.model.local.preferences.dataStore
import com.example.wetterbericht.model.repository.localrepository.LocalRepository

object Injection {
    fun provideLocalRepository(context : Context): LocalRepository {
        return LocalRepository(
            LocalDatabase.setDatabase(context),
            OnboardingPreferences.getInstance(context.dataStore)
        )
    }

    fun providedUseCase(context: Context): LocalUseCase{
        val localRepository = providedRepository(context)
        return LocalInteractor(localRepository)
    }

    private fun providedRepository(context: Context): ILocalRepository{
        val localDataSource = provideDataSource(context)
        //at com.example.wetterbericht.injection.Injection.providedRepository(Injection.kt:29)
        return TodoRepository(localDataSource)
    }

    fun provideDataSource(context: Context): ILocalDataSource{
        return LocalDataSource(context)
    }

}