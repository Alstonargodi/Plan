package com.example.wetterbericht.data.local.source.datauser

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.preferences.UserProfile
import kotlinx.coroutines.flow.Flow

interface IProfileSource {
    fun getProfilePreferences(): Flow<UserProfile>
    suspend fun saveProfilePreferences(profile: UserProfile)
}