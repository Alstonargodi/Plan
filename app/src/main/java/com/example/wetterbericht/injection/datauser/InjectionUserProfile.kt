package com.example.wetterbericht.injection.datauser

import android.content.Context
import com.example.wetterbericht.data.local.source.datauser.IProfileSource
import com.example.wetterbericht.data.local.source.datauser.ProfileSource
import com.example.wetterbericht.data.repository.local.datauser.IProfileRepository
import com.example.wetterbericht.data.repository.local.datauser.ProfileRepository
import com.example.wetterbericht.domain.localusecase.datauser.ProfileUseCase

object InjectionUserProfile {
    fun provideProfileUseCase(context: Context): ProfileUseCase{
        return ProfileUseCase(
            provideProfileRepository(context)
        )
    }
    private fun provideProfileRepository(context: Context): IProfileRepository{
        return ProfileRepository(
            provideProfileSource(context)
        )
    }
    private fun provideProfileSource(context: Context): IProfileSource{
        return ProfileSource(context)
    }
}