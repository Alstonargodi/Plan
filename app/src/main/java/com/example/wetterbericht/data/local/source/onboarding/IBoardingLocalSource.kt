package com.example.wetterbericht.data.local.source.onboarding

import androidx.lifecycle.LiveData

interface IBoardingLocalSource {
    //onboarding
    fun getOnBoardingStatus(): LiveData<Boolean>
    suspend fun saveOnBoardingStatus(onBoard : Boolean)
}