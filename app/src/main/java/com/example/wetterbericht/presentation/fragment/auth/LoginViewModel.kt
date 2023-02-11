package com.example.wetterbericht.presentation.fragment.auth

import androidx.lifecycle.ViewModel
import com.example.wetterbericht.data.remote.firebase.IFirebaseAuthService
import com.example.wetterbericht.domain.remoteusecase.firebase.FirebaseAuthUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class LoginViewModel(
    private val firebase : FirebaseAuthUseCase
): IFirebaseAuthService, ViewModel() {
    override fun authLoginUser(email: String, password: String): Task<AuthResult> =
        firebase.authLoginUser(email, password)

    override fun authSignUpUser(email: String, password: String): Task<AuthResult> =
        firebase.authSignUpUser(email, password)

}