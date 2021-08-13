package com.example.wetterbericht.model.api

import com.example.wetterbericht.model.builder.constant.apikey
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//http://api.weatherstack.com/current?access_key=dd6efebd921e31715876a6909b7cc103&query=New York
/*
    Lang = language
    query = lokasi
    a
 */
interface Getapi {

    @GET("current")
    fun getdata(
        @Query("query") location: Any,
        @Query("access_key") api_key : String = apikey
    ): Response<Weather>

}