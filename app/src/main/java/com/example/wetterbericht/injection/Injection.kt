package com.example.wetterbericht.injection

import android.content.Context
import com.example.wetterbericht.data.repository.local.ILocalRepository
import com.example.wetterbericht.domain.localusecase.LocalInteractor
import com.example.wetterbericht.domain.localusecase.LocalUseCase
import com.example.wetterbericht.data.local.source.ILocalDataSource
import com.example.wetterbericht.data.local.source.LocalDataSource
import com.example.wetterbericht.data.remote.config.ApiConfig
import com.example.wetterbericht.data.remote.source.WeatherDataSource
import com.example.wetterbericht.data.repository.local.LocalRepository
import com.example.wetterbericht.data.repository.remote.IRemoteRepository
import com.example.wetterbericht.data.repository.remote.RemoteRepository
import com.example.wetterbericht.domain.remoteusecase.RemoteInteractor
import com.example.wetterbericht.domain.remoteusecase.RemoteUseCase

object Injection {

    fun providedUseCase(context: Context): LocalUseCase {
        val localRepository = providedRepository(context)
        return LocalInteractor(localRepository)
    }

    private fun providedRepository(context: Context): ILocalRepository {
        val localDataSource = provideDataSource(context)
        return LocalRepository(localDataSource)
    }

    private fun provideDataSource(context: Context): ILocalDataSource {
        return LocalDataSource(context)
    }

    fun provideWeatherUseCase(): RemoteUseCase{
        return RemoteInteractor(provideWeatherRepository())
    }

    private fun provideWeatherRepository(): IRemoteRepository{
        return RemoteRepository(provideWeatherDatasource())
    }

    private fun provideWeatherDatasource(): WeatherDataSource{
        return WeatherDataSource(ApiConfig.getApiService())
    }

}