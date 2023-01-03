package com.example.wetterbericht.domain.localusecase.boarding

import androidx.lifecycle.LiveData

interface BoardingLocalUseCase {
    //onboarding
    fun getOnBoardingStatus(): LiveData<Boolean>
    suspend fun saveOnBoardingStatus(onBoard : Boolean)
}