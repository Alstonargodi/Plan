package com.example.wetterbericht.domain.localusecase.datauser

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.preferences.UserProfile
import kotlinx.coroutines.flow.Flow

interface IProfileUseCase {
    fun getProfilePreferences(): Flow<UserProfile>
    suspend fun saveProfilePreferences(profile: UserProfile)
}