package com.example.wetterbericht.domain.remoteusecase.firebase

import com.example.wetterbericht.data.remote.firebase.authentication.IFirebaseAuthService
import com.example.wetterbericht.data.repository.remote.firebase.FirebaseAuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class FirebaseAuthUseCase(
    private val repository: FirebaseAuthRepository
): IFirebaseAuthService {
    override fun authLoginUser(email: String, password: String): Task<AuthResult> =
        repository.authLoginUser(email, password)

    override fun authSignUpUser(email: String, password: String): Task<AuthResult> =
        repository.authSignUpUser(email, password)
}