package com.example.wetterbericht.injection

import android.content.Context
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
}