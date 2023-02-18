package com.example.wetterbericht.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wetterbericht.domain.localusecase.weather.IWeatherLocalUseCase
import com.example.wetterbericht.domain.localusecase.boarding.BoardingLocalUseCase
import com.example.wetterbericht.domain.localusecase.datauser.ProfileUseCase
import com.example.wetterbericht.domain.localusecase.habits.HabitsLocalUseCase
import com.example.wetterbericht.domain.localusecase.todotask.TodoLocalUseCase
import com.example.wetterbericht.domain.remoteusecase.firebase.FirebaseAuthUseCase
import com.example.wetterbericht.domain.remoteusecase.weather.IOpenWeatherUseCase
import com.example.wetterbericht.injection.Injection
import com.example.wetterbericht.injection.boarding.InjectionBoarding
import com.example.wetterbericht.injection.datauser.InjectionUserProfile
import com.example.wetterbericht.injection.habits.InjectionHabits
import com.example.wetterbericht.injection.todo.InjectionTodo
import com.example.wetterbericht.presentation.activity.onboarding.OnBoardingViewModel
import com.example.wetterbericht.presentation.fragment.auth.LoginViewModel
import com.example.wetterbericht.presentation.fragment.habits.viewmodel.HabitsViewModel
import com.example.wetterbericht.presentation.fragment.home.viewmodel.HomeViewModel
import com.example.wetterbericht.presentation.fragment.insertnewtask.viewmodel.InsertTodoViewModel
import com.example.wetterbericht.presentation.fragment.stats.StatisticFragmentViewModel
import com.example.wetterbericht.presentation.fragment.weather.weatherviewmodel.WeatherViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
    private val IWeatherLocalUseCase: IWeatherLocalUseCase,
    private val IOpenWeatherUseCase: IOpenWeatherUseCase,
    private val habitsLocalUseCase: HabitsLocalUseCase,
    private val todoUseCase: TodoLocalUseCase,
    private val boardingLocalUseCase: BoardingLocalUseCase,
    private val firebaseAuthUseCase : FirebaseAuthUseCase,
    private val profileUseCase: ProfileUseCase
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
                        InjectionHabits.provideHabitsUseCase(context),
                        InjectionTodo.provideTodoUseCase(context),
                        InjectionBoarding.provideBoardingUseCase(context),
                        Injection.provideFirebaseAuthUseCase(),
                        InjectionUserProfile.provideProfileUseCase(context)
                    )
                }
            }
            return instance as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            return WeatherViewModel(
                IOpenWeatherUseCase,
                IWeatherLocalUseCase
            ) as T
        }
        else if(modelClass.isAssignableFrom(OnBoardingViewModel::class.java)){
            return OnBoardingViewModel(boardingLocalUseCase) as T
        }
        else if(modelClass.isAssignableFrom(HabitsViewModel::class.java)){
            return HabitsViewModel(habitsLocalUseCase) as T
        }
        else if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(
                todoUseCase,
                IWeatherLocalUseCase
            ) as T
        }
        else if (modelClass.isAssignableFrom(InsertTodoViewModel::class.java)){
            return InsertTodoViewModel(
                todoUseCase,
                habitsLocalUseCase
            ) as T
        }
        else if (modelClass.isAssignableFrom(StatisticFragmentViewModel::class.java)){
            return StatisticFragmentViewModel(todoUseCase) as T
        }
        else if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(
                firebaseAuthUseCase,
                profileUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}