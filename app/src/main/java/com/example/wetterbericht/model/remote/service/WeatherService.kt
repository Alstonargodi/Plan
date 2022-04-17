package com.example.wetterbericht.model.remote.service

import com.example.wetterbericht.model.remote.response.ForecastResponse
import com.example.wetterbericht.model.remote.constant.Apikey_
import com.example.wetterbericht.model.remote.constant.satuan
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface WeatherService {
    @GET("weather")
    fun getDataBySearch(
        @Query("q") Location : Any,
        @Query("units") units : String = satuan,
        @Query("appid") api_key : String = Apikey_
    ): Call<WeatherResponse>

    @GET("weather")
    suspend fun getDatabyLocation(
        @Query("lat") Lot : Double,
        @Query("lon") lon : Double,
        @Query("units") units : String = satuan,
        @Query("appid") Apikey : String = Apikey_
    ): Response<WeatherResponse>

    @GET("forecast")
    fun getForecast(
        @Query("q") Location: Any,
        @Query("units") units : String = satuan,
        @Query("appid") apikey : String = Apikey_
    ): Call<ForecastResponse>


}