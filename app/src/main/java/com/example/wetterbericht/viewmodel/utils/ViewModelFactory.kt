package com.example.wetterbericht.viewmodel.utils

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wetterbericht.model.injection.Injection
import com.example.wetterbericht.model.repository.LocalRepository
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.remote.WeatherViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val localRepository: LocalRepository)
    : ViewModelProvider.NewInstanceFactory()
{
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            if (instance == null){
                synchronized(ViewModelFactory::class.java){
                    instance = ViewModelFactory(Injection.provideLocalRepository(context))
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
            return LocalViewModel(localRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}