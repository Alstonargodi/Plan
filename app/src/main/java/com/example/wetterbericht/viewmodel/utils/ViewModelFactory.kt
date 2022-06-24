package com.example.wetterbericht.viewmodel.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.remote.WeatherViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val application: Application)
    : ViewModelProvider.NewInstanceFactory()
{
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(application: Application): ViewModelFactory {
            if (instance == null){
                synchronized(ViewModelFactory::class.java){
                    instance = ViewModelFactory(application)
                }
            }
            return instance as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            return WeatherViewModel() as T
        }
        else if (modelClass.isAssignableFrom(LocalViewModel::class.java)){
            return LocalViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}