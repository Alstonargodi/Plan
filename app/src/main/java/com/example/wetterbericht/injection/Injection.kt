package com.example.wetterbericht.injection

import android.content.Context
import com.example.wetterbericht.data.repository.local.weather.IWeatherLocalRepository
import com.example.wetterbericht.domain.localusecase.weather.WeatherInteractor
import com.example.wetterbericht.domain.localusecase.weather.WeatherUseCase
import com.example.wetterbericht.data.local.source.weather.IWeatherLocalDataSource
import com.example.wetterbericht.data.local.source.weather.WeatherLocalDataSource
import com.example.wetterbericht.data.remote.config.ApiConfig
import com.example.wetterbericht.data.remote.source.WeatherDataSource
import com.example.wetterbericht.data.repository.local.weather.WeatherLocalRepository
import com.example.wetterbericht.data.repository.remote.IRemoteRepository
import com.example.wetterbericht.data.repository.remote.RemoteRepository
import com.example.wetterbericht.domain.remoteusecase.RemoteInteractor
import com.example.wetterbericht.domain.remoteusecase.RemoteUseCase

object Injection {

    fun providedUseCase(context: Context): WeatherUseCase {
        val localRepository = providedRepository(context)
        return WeatherInteractor(localRepository)
    }

    private fun providedRepository(context: Context): IWeatherLocalRepository {
        val localDataSource = provideDataSource(context)
        return WeatherLocalRepository(localDataSource)
    }

    private fun provideDataSource(context: Context): IWeatherLocalDataSource {
        return WeatherLocalDataSource(context)
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