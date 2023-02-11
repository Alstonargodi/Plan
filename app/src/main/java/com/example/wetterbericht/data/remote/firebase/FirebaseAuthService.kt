package com.example.wetterbericht.data.remote.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthService: IFirebaseAuthService {
    override fun authLoginUser(email: String, password: String): Task<AuthResult> {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email, password
        )
    }

    override fun authSignUpUser(email: String, password: String): Task<AuthResult> {
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email, password
        )
    }

}