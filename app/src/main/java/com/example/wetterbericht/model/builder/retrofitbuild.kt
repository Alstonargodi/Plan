package com.example.wetterbericht.model.builder

import com.example.wetterbericht.model.api.Getapi
import com.example.wetterbericht.model.builder.constant.base_url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitbuild {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : Getapi by lazy {
        retrofit.create(Getapi::class.java)
    }

}