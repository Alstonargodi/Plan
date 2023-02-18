package com.example.wetterbericht.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.UserDatapreferenceStore : DataStore<Preferences> by preferencesDataStore(
    "UserDataPrefereneces"
)
class UserDataPreferences(
    private val dataStore : DataStore<Preferences>
) {
    private val userIdPreference = stringPreferencesKey("useId_key")

    fun getUserIdPreferences(): Flow<String> {
        return dataStore.data.map {
            it[userIdPreference] ?: "nouserid"
        }
    }

    suspend fun saveUserIdPreferences(userId : String){
        dataStore.edit {
            it[userIdPreference] = userId
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