package com.example.wetterbericht.injection.todo

import com.example.wetterbericht.data.remote.firebase.authentication.FirebaseAuthService
import com.example.wetterbericht.data.repository.remote.firebase.FirebaseAuthRepository
import com.example.wetterbericht.domain.remoteusecase.firebase.FirebaseAuthUseCase

object InjectionFirebaseAuth {
    fun provideFirebaseAuthUseCase(): FirebaseAuthUseCase {
        return FirebaseAuthUseCase(provideFirebaseAuthRepo())
    }

    fun provideFirebaseAuthRepo(): FirebaseAuthRepository {
        return FirebaseAuthRepository(FirebaseAuthService())
    }
}