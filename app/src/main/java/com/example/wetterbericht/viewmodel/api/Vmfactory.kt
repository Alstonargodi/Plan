package com.example.wetterbericht.viewmodel.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wetterbericht.model.repo.api.WeatherRepository

class Vmfactory(val repo: WeatherRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            return WeatherViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }


}