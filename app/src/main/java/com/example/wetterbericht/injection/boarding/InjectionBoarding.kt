package com.example.wetterbericht.injection.boarding

import android.content.Context
import com.example.wetterbericht.data.local.source.onboarding.BoardingLocalSource
import com.example.wetterbericht.data.local.source.onboarding.IBoardingLocalSource
import com.example.wetterbericht.data.repository.local.onboarding.BoardingLocalRepository
import com.example.wetterbericht.data.repository.local.onboarding.IBoardingLocalRepository
import com.example.wetterbericht.domain.localusecase.boarding.BoardingLocalInteractor
import com.example.wetterbericht.domain.localusecase.boarding.BoardingLocalUseCase

object InjectionBoarding {
    fun provideBoardingUseCase(context: Context): BoardingLocalUseCase{
        return BoardingLocalInteractor(provideRepository(context))
    }
    private fun provideRepository(context: Context): IBoardingLocalRepository{
        return BoardingLocalRepository(provideBoardingSource(context))
    }
    private fun provideBoardingSource(context: Context): IBoardingLocalSource{
        return BoardingLocalSource(context)
    }
}