package com.example.wetterbericht.data.repository.local.onboarding

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.source.onboarding.IBoardingLocalSource

class BoardingLocalRepository(
    private val dataSource: IBoardingLocalSource
): IBoardingLocalRepository{
    override fun getOnBoardingStatus(): LiveData<Boolean> {
        return dataSource.getOnBoardingStatus()
    }

    override suspend fun saveOnBoardingStatus(onBoard: Boolean) {
        dataSource.saveOnBoardingStatus(onBoard)
    }
}