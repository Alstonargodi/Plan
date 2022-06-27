package com.example.wetterbericht.model.injection

import android.content.Context
import com.example.wetterbericht.model.local.database.LocalDatabase
import com.example.wetterbericht.model.repository.LocalRepository

object Injection {
    fun provideLocalRepository(context : Context): LocalRepository{
        return LocalRepository(LocalDatabase.setDatabase(context))
    }
}