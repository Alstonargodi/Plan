package com.example.wetterbericht.model.APIweather

import com.example.wetterbericht.model.APIforecast.Forecast
import com.example.wetterbericht.model.APIforecast.mainfore
import com.example.wetterbericht.model.builder.constant.Apikey_
import com.example.wetterbericht.model.builder.constant.satuan
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//cur weather = api.openweathermap.org/data/2.5/weather?q=surabaya&appid=adede588548f908529494b8da290c4e9
//cur forecast = api.openweathermap.org/data/2.5/forecast?q=surabaya&appid=adede588548f908529494b8da290c4e9

interface getapi {
    @GET("weather")
    suspend fun getdata(
        @Query("q") Location : Any,
        @Query("units") units : String = satuan,
        @Query("appid") api_key : String = Apikey_
    ): Response<Current>

    @GET("weather")
    suspend fun getdatalocation(
        @Query("lat") Lot : Double,
        @Query("lon") lon : Double,
        @Query("units") units : String = satuan,
        @Query("appid") Apikey : String = Apikey_
    ): Response<Current>

    @GET("forecast")
    suspend fun getforecast(
        @Query("q") Location: Any,
        @Query("units") units : String = satuan,
        @Query("appid") apikey : String = Apikey_
    ): Response <Forecast>


}