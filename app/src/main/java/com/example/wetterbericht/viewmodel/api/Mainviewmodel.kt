package com.example.wetterbericht.viewmodel.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wetterbericht.model.APIforecast.Forecast
import com.example.wetterbericht.model.APIforecast.mainfore
import com.example.wetterbericht.model.APIweather.Current
import com.example.wetterbericht.repo.api.mainrepo
import kotlinx.coroutines.launch
import retrofit2.Response

class Mainviewmodel(val repo : mainrepo): ViewModel() {
    val datarespon : MutableLiveData<Response<Current>> = MutableLiveData()
    val datalocationrespon : MutableLiveData<Response<Current>> = MutableLiveData()
    val forecastrespon : MutableLiveData<Response<Forecast>> = MutableLiveData()

    fun getdata(loc : Any){
        viewModelScope.launch {
            val respon = repo.getdata(loc)
            datarespon.value = respon
        }
    }

    fun getdatalocation(lat : Double,lon : Double){
        viewModelScope.launch {
            val locrespon = repo.getdatalocation(lat, lon)
            datalocationrespon.value = locrespon
        }
    }

    fun getforecast(loc: Any){
        viewModelScope.launch {
            val respon = repo.getforecast(loc)
            forecastrespon.value = respon
        }
    }

}