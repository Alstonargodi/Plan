package com.example.wetterbericht.domain.localusecase.boarding

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.source.onboarding.BoardingLocalSource
import com.example.wetterbericht.data.repository.local.boarding.IBoardingLocalRepository

class BoardingLocalInteractor(
    private val repository: IBoardingLocalRepository
): BoardingLocalUseCase {
    override fun getOnBoardingStatus(): LiveData<Boolean> {
        return repository.getOnBoardingStatus()
    }
    override suspend fun saveOnBoardingStatus(onBoard: Boolean) {
        return repository.saveOnBoardingStatus(onBoard)
    }
}