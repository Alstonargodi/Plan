package com.example.wetterbericht.model.repo.api

import com.example.wetterbericht.model.APIforecast.Forecast
import com.example.wetterbericht.model.builder.retrofitbuild
import com.example.wetterbericht.model.APIweather.Current
import retrofit2.Response

class mainrepo(){
        suspend fun getdata(loc : Any): Response<Current>{
            return retrofitbuild.api.getdata(loc)
        }

        suspend fun getdatalocation(lat : Double,lon : Double): Response<Current>{
            return retrofitbuild.api.getdatalocation(lat,lon)
        }

        suspend fun getforecast(loc: Any): Response<Forecast>{
         return retrofitbuild.api.getforecast(loc)
        }
}