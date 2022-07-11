package com.example.wetterbericht.injection

import android.content.Context
import com.example.wetterbericht.domain.ILocalRepository
import com.example.wetterbericht.domain.localusecase.LocalInteractor
import com.example.wetterbericht.domain.localusecase.LocalUseCase
import com.example.wetterbericht.data.local.source.ILocalDataSource
import com.example.wetterbericht.data.local.source.LocalDataSource
import com.example.wetterbericht.data.repository.LocalRepository

object Injection {

    fun providedUseCase(context: Context): LocalUseCase {
        val localRepository = providedRepository(context)
        return LocalInteractor(localRepository)
    }

    private fun providedRepository(context: Context): ILocalRepository{
        val localDataSource = provideDataSource(context)
        return LocalRepository(localDataSource)
    }

    private fun provideDataSource(context: Context): ILocalDataSource {
        return LocalDataSource(context)
    }

}