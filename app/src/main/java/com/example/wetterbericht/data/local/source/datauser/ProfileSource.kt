package com.example.wetterbericht.data.local.source.datauser

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.wetterbericht.data.local.preferences.UserDataPreferences
import com.example.wetterbericht.data.local.preferences.UserProfile
import com.example.wetterbericht.data.local.preferences.dataStore
import kotlinx.coroutines.flow.Flow

class ProfileSource(context: Context): IProfileSource{
    private val userData = UserDataPreferences.getInstance(context.dataStore)

    override fun getProfilePreferences(): Flow<UserProfile> {
       return userData.getProfilePreferences()
    }

    override suspend fun saveProfilePreferences(profile: UserProfile) {
        userData.saveProfilePreferences(profile)
    }

}