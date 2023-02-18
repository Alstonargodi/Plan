package com.example.wetterbericht.data.repository.local.datauser

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.preferences.UserProfile
import com.example.wetterbericht.data.local.source.datauser.IProfileSource
import kotlinx.coroutines.flow.Flow

class ProfileRepository(
    private val dataSource : IProfileSource
): IProfileRepository {
    override fun getProfileReferences(): Flow<UserProfile> {
        return dataSource.getProfilePreferences()
    }

    override suspend fun saveProfilePreferences(profile: UserProfile) {
       return dataSource.saveProfilePreferences(profile)
    }

}