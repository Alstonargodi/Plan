package com.example.wetterbericht.viewmodel.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wetterbericht.domain.localusecase.LocalUseCase
import com.example.wetterbericht.domain.localusecase.habits.HabitsLocalUseCase
import com.example.wetterbericht.domain.remoteusecase.RemoteUseCase
import com.example.wetterbericht.injection.Injection
import com.example.wetterbericht.injection.habits.InjectionHabits
import com.example.wetterbericht.presentation.fragment.habits.viewmodel.HabitsViewModel
import com.example.wetterbericht.viewmodel.localviewmodel.LocalViewModel
import com.example.wetterbericht.viewmodel.weatherviewmodel.WeatherViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
    private val localUseCase: LocalUseCase,
    private val remoteUseCase: RemoteUseCase,
    private val habitsLocalUseCase: HabitsLocalUseCase
) : ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            if (instance == null){
                synchronized(ViewModelFactory::class.java){
                    instance = ViewModelFactory(
                        Injection.providedUseCase(context),
                        Injection.provideWeatherUseCase(),
                        InjectionHabits.provideHabitsUseCase(context)
                    )
                }
            }
            return instance as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            return WeatherViewModel(remoteUseCase) as T
        }
        else if (modelClass.isAssignableFrom(LocalViewModel::class.java)){
            return LocalViewModel(localUseCase) as T
        }
        else if(modelClass.isAssignableFrom(HabitsViewModel::class.java)){
            return HabitsViewModel(habitsLocalUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}