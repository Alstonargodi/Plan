package com.example.wetterbericht.presentation.activity.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wetterbericht.domain.localusecase.boarding.BoardingLocalUseCase

class OnBoardingViewModel(
    private val useCase: BoardingLocalUseCase
): ViewModel() {
    fun getOnBoardingStatus(): LiveData<Boolean> =
        useCase.getOnBoardingStatus()

    suspend fun saveOnBoardingStatus(onBoard : Boolean){
        useCase.saveOnBoardingStatus(onBoard)
    }
}