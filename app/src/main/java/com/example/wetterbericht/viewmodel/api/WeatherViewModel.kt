package com.example.wetterbericht.viewmodel.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wetterbericht.model.response.ForecastResponse
import com.example.wetterbericht.model.service.WeatherResponse
import com.example.wetterbericht.model.repo.api.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(): ViewModel() {
    val repo = WeatherRepository()
    val weatherSearchRespon : MutableLiveData<WeatherResponse> = MutableLiveData()
    val weatherLocationRespon : MutableLiveData<Response<WeatherResponse>> = MutableLiveData()
    val forecastRespon : MutableLiveData<ForecastResponse> = MutableLiveData()

    val isLoading : MutableLiveData<Boolean> = MutableLiveData()

    fun getWeatherSearch(loc : Any){
        isLoading.value = true
        repo.getWeatherBySearch(loc).enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
               if (response.isSuccessful){
                   isLoading.value = false
                   weatherSearchRespon.value = response.body()
               }else{
                   isLoading.value = false
                   Log.d(tag,response.message())
               }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                isLoading.value = false
                Log.d(tag,t.message.toString())
            }
        })
    }

    fun getWeatherLocation(lat : Double, lon : Double){
        isLoading.value = true
        viewModelScope.launch {
            weatherLocationRespon.value = repo.getWeatherByLocation(lat, lon)
            isLoading.value = false
        }
    }

    fun getWeatherForecast(loc: Any){
        isLoading.value = true
        repo.getWeatherForecast(loc).enqueue(object : Callback<ForecastResponse>{
            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                if (response.isSuccessful){
                    isLoading.value = false
                    forecastRespon.value = response.body()
                }else{
                    isLoading.value = false
                    Log.d(tag,response.message())
                }
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.d(tag,t.message.toString())
            }
        })
    }

    companion object{
        val tag = "weatherviewmodel"
    }

}