package com.example.wetterbericht.injection

import android.content.Context
import com.example.wetterbericht.data.repository.local.weather.IWeatherLocalRepository
import com.example.wetterbericht.domain.localusecase.weather.IWeatherLocalLocalUseCase
import com.example.wetterbericht.domain.localusecase.weather.IWeatherLocalUseCase
import com.example.wetterbericht.data.local.source.weather.IWeatherLocalDataSource
import com.example.wetterbericht.data.local.source.weather.WeatherLocalDataSource
import com.example.wetterbericht.data.remote.firebase.FirebaseAuthService
import com.example.wetterbericht.data.remote.weather.config.ApiConfig
import com.example.wetterbericht.data.remote.weather.source.OpenWeatherDataSource
import com.example.wetterbericht.data.repository.local.weather.WeatherLocalRepository
import com.example.wetterbericht.data.repository.remote.firebase.FirebaseAuthRepository
import com.example.wetterbericht.data.repository.remote.weather.IOpenWeatherRepository
import com.example.wetterbericht.data.repository.remote.weather.OpenWeatherRepository
import com.example.wetterbericht.domain.remoteusecase.firebase.FirebaseAuthUseCase
import com.example.wetterbericht.domain.remoteusecase.weather.OpenWeatherUseCase
import com.example.wetterbericht.domain.remoteusecase.weather.IOpenWeatherUseCase

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

    fun provideWeatherUseCase(): IOpenWeatherUseCase {
        return OpenWeatherUseCase(provideWeatherRepository())
    }

    private fun provideWeatherRepository(): IOpenWeatherRepository {
        return OpenWeatherRepository(provideWeatherDatasource())
    }

    private fun provideWeatherDatasource(): OpenWeatherDataSource{
        return OpenWeatherDataSource(ApiConfig.getApiService())
    }

    fun provideFirebaseAuthUseCase(): FirebaseAuthUseCase{
        return FirebaseAuthUseCase(provideFirebaseAuthRepo())
    }

    fun provideFirebaseAuthRepo(): FirebaseAuthRepository{
        return FirebaseAuthRepository(FirebaseAuthService())
    }

}