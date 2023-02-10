package com.example.wetterbericht.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "onBoardingPreferences")

class OnboardingPreferences(private val dataStore : DataStore<Preferences>) {
    private val onBoardingKey = booleanPreferencesKey("onBoarding_key")

    fun getOnboardingStatus(): Flow<Boolean> {
        return dataStore.data.map {
            it[onBoardingKey] ?: false
        }
    }

    suspend fun savePreferences(onBoard : Boolean){
        dataStore.edit {
            it[onBoardingKey] = onBoard
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: OnboardingPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>):OnboardingPreferences{
            return INSTANCE ?: synchronized(this){
                val instance = OnboardingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}