package com.example.wetterbericht.data.repository.remote.firebase

import com.example.wetterbericht.data.remote.firebase.FirebaseAuthService
import com.example.wetterbericht.data.remote.firebase.IFirebaseAuthService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class FirebaseAuthRepository(
    private val service : FirebaseAuthService
): IFirebaseAuthService {
    override fun authLoginUser(email: String, password: String): Task<AuthResult> =
        service.authLoginUser(email, password)

    override fun authSignUpUser(email: String, password: String): Task<AuthResult> =
        service.authSignUpUser(email, password)

}