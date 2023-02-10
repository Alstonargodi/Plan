package com.example.wetterbericht.data.repository.local.onboarding

import androidx.lifecycle.LiveData

interface IBoardingLocalRepository {
    //onboarding
    fun getOnBoardingStatus(): LiveData<Boolean>
    suspend fun saveOnBoardingStatus(onBoard : Boolean)
}