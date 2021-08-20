package com.example.wetterbericht.model.builder

import com.example.wetterbericht.model.builder.constant.base_url
import com.example.wetterbericht.model.APIweather.getapi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitbuild {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : getapi by lazy {
        retrofit.create(getapi::class.java)
    }

}