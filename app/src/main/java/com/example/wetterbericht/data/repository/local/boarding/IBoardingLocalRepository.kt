package com.example.wetterbericht.data.repository.local.boarding

import androidx.lifecycle.LiveData

interface IBoardingLocalRepository {
    //onboarding
    fun getOnBoardingStatus(): LiveData<Boolean>
    suspend fun saveOnBoardingStatus(onBoard : Boolean)
}