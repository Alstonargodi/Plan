package com.example.wetterbericht.viewmodel.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wetterbericht.model.api.Weather
import com.example.wetterbericht.repo.api.mainrepo
import kotlinx.coroutines.launch
import retrofit2.Response

class Mainviewmodel(val repo : mainrepo): ViewModel() {
        val datarespon : MutableLiveData<Response<Weather>> = MutableLiveData()

    fun getdata(loc : Any){
        viewModelScope.launch {
            val respon = repo.getdata(loc)
            datarespon.value = respon
        }
    }

}