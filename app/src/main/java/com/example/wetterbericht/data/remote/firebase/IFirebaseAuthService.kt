package com.example.wetterbericht.data.remote.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface IFirebaseAuthService {
    fun authLoginUser(email : String,password : String): Task<AuthResult>
    fun authSignUpUser(email : String,password : String): Task<AuthResult>
}