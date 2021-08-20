package com.example.wetterbericht.model.APIweather

import com.example.wetterbericht.model.APIforecast.mainfore
import com.example.wetterbericht.model.builder.constant.Apikey_
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//cur weather = api.openweathermap.org/data/2.5/weather?q=surabaya&appid=adede588548f908529494b8da290c4e9
//cur forecast = api.openweathermap.org/data/2.5/forecast?q=surabaya&appid=adede588548f908529494b8da290c4e9

interface getapi {
    @GET("weather")
    suspend fun getdata(
        @Query("q") Location : Any,
        @Query("appid") api_key : String = Apikey_
    ): Response<Current>

    @GET("forecast")
    suspend fun getforecast(
        @Query("q") Location: Any,
        @Query("appid") apikey : String = Apikey_
    ): Response <mainfore>
}