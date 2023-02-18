package com.example.wetterbericht.domain.localusecase.datauser

import androidx.lifecycle.LiveData
import com.example.wetterbericht.data.local.preferences.UserProfile
import com.example.wetterbericht.data.repository.local.datauser.IProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileUseCase(
    private val repository : IProfileRepository
): IProfileUseCase {
    override fun getProfilePreferences(): Flow<UserProfile> {
        return repository.getProfileReferences()
    }

    override suspend fun saveProfilePreferences(profile: UserProfile) {
       repository.saveProfilePreferences(profile)
    }

}