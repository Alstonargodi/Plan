package com.example.wetterbericht.viewmodel.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wetterbericht.injection.Injection
import com.example.wetterbericht.model.repository.localrepository.LocalRepository
import com.example.wetterbericht.viewmodel.localviewmodel.LocalViewModel
import com.example.wetterbericht.viewmodel.weatherviewmodel.WeatherViewModel

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