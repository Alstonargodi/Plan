package com.example.wetterbericht.data.remote.weather.config

import com.example.wetterbericht.BuildConfig
import com.example.wetterbericht.data.remote.weather.utils.ApiUtils.weatherBaseURL
import com.example.wetterbericht.data.remote.weather.service.OpenWeatherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    fun getApiService(): OpenWeatherService {
        val loggingInterceptor =
            if(BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(weatherBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(OpenWeatherService::class.java)
    }
}