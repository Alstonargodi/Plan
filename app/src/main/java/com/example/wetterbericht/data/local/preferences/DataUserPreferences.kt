package com.example.wetterbericht.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.mutablePreferencesOf


val Context.UserDatapreferenceStore : DataStore<Preferences> by preferencesDataStore(
    "UserDataPrefereneces"
)
class UserDataPreferences(
    private val dataStore : DataStore<Preferences>
) {
    private val userIdPreference = stringPreferencesKey("useId_key")
    private val emailPreference = stringPreferencesKey("email_key")


    fun getProfilePreferences(): Flow<UserProfile>{
        return dataStore.data.map {
            UserProfile(
                it[emailPreference] ?: "noemail",
                it[userIdPreference] ?: "nouserid"
            )
        }
    }

    suspend fun saveProfilePreferences(profile : UserProfile){
        dataStore.edit {
            it[emailPreference] = profile.email
            it[userIdPreference] = profile.userId
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: UserDataPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserDataPreferences{
            return INSTANCE ?: synchronized(this){
                val instance = UserDataPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}