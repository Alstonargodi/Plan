package com.example.wetterbericht.data.local.source.onboarding

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.wetterbericht.data.local.preferences.OnboardingPreferences
import com.example.wetterbericht.data.local.preferences.dataStore
import java.util.concurrent.Executors

class BoardingLocalSource(
    val context: Context
): IBoardingLocalSource {
    private val onBoardingPreferences = OnboardingPreferences.getInstance(context.dataStore)

    override fun getOnBoardingStatus(): LiveData<Boolean> {
        return onBoardingPreferences.getOnboardingStatus().asLiveData()
    }

    override suspend fun saveOnBoardingStatus(onBoard: Boolean) {
        onBoardingPreferences.savePreferences(onBoard)
    }
}