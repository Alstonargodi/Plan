package com.example.wetterbericht.data.repository.local.boarding

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.source.onboarding.BoardingLocalSource
import com.example.wetterbericht.data.local.source.onboarding.IBoardingLocalSource

class BoardingLocalRespository(
    private val dataSource: IBoardingLocalSource
): IBoardingLocalRepository{
    override fun getOnBoardingStatus(): LiveData<Boolean> {
        return dataSource.getOnBoardingStatus()
    }

    override suspend fun saveOnBoardingStatus(onBoard: Boolean) {
        dataSource.saveOnBoardingStatus(onBoard)
    }
}