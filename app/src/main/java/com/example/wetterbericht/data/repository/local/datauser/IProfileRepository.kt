package com.example.wetterbericht.data.repository.local.datauser

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.preferences.UserProfile
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {
    fun getProfileReferences(): Flow<UserProfile>
    suspend fun saveProfilePreferences(profile: UserProfile)
}