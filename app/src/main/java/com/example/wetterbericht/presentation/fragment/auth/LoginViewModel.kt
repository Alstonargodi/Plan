package com.example.wetterbericht.presentation.fragment.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.wetterbericht.data.local.preferences.UserProfile
import com.example.wetterbericht.data.remote.firebase.IFirebaseAuthService
import com.example.wetterbericht.domain.localusecase.datauser.IProfileUseCase
import com.example.wetterbericht.domain.localusecase.datauser.ProfileUseCase
import com.example.wetterbericht.domain.remoteusecase.firebase.FirebaseAuthUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

class LoginViewModel(
    private val firebase : FirebaseAuthUseCase,
    private val userLocal : ProfileUseCase,
): IFirebaseAuthService, ViewModel() {

    override fun authLoginUser(email: String, password: String): Task<AuthResult> =
        firebase.authLoginUser(email, password)

    override fun authSignUpUser(email: String, password: String): Task<AuthResult> =
        firebase.authSignUpUser(email, password)

    fun getProfilePreferences(): LiveData<UserProfile> {
        return userLocal.getProfilePreferences().asLiveData()
    }

    suspend fun saveProfilePreferences(profile: UserProfile) {
       userLocal.saveProfilePreferences(profile)
    }

}