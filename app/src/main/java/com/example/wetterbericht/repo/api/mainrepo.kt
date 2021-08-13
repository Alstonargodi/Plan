package com.example.wetterbericht.repo.api

import com.example.wetterbericht.model.api.Weather
import com.example.wetterbericht.model.builder.retrofitbuild
import retrofit2.Response

class mainrepo(){
        suspend fun getdata(loc : Any): Response<Weather>{
            return retrofitbuild.api.getdata(loc)
        }
}