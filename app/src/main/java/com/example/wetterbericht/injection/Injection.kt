package com.example.wetterbericht.injection

import android.content.Context
import com.example.wetterbericht.data.repository.local.weather.IWeatherLocalRepository
import com.example.wetterbericht.domain.localusecase.weather.IWeatherLocalLocalUseCase
import com.example.wetterbericht.domain.localusecase.weather.IWeatherLocalUseCase
import com.example.wetterbericht.data.local.source.weather.IWeatherLocalDataSource
import com.example.wetterbericht.data.local.source.weather.WeatherLocalDataSource
import com.example.wetterbericht.data.remote.config.ApiConfig
import com.example.wetterbericht.data.remote.source.OpenWeatherDataSource
import com.example.wetterbericht.data.repository.local.weather.WeatherLocalRepository
import com.example.wetterbericht.data.repository.remote.IOpenWeatherRepository
import com.example.wetterbericht.data.repository.remote.OpenWeatherRepository
import com.example.wetterbericht.domain.remoteusecase.OpenWeatherUseCase
import com.example.wetterbericht.domain.remoteusecase.IOpenWeatherUseCase

object Injection {

    fun providedUseCase(context: Context): IWeatherLocalUseCase {
        val localRepository = providedRepository(context)
        return IWeatherLocalLocalUseCase(localRepository)
    }

    private fun providedRepository(context: Context): IWeatherLocalRepository {
        val localDataSource = provideDataSource(context)
        return WeatherLocalRepository(localDataSource)
    }

    private fun provideDataSource(context: Context): IWeatherLocalDataSource {
        return WeatherLocalDataSource(context)
    }


    fun provideWeatherUseCase(): IOpenWeatherUseCase{
        return OpenWeatherUseCase(provideWeatherRepository())
    }

    private fun provideWeatherRepository(): IOpenWeatherRepository{
        return OpenWeatherRepository(provideWeatherDatasource())
    }

    private fun provideWeatherDatasource(): OpenWeatherDataSource{
        return OpenWeatherDataSource(ApiConfig.getApiService())
    }

}