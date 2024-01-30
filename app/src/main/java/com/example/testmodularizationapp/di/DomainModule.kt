package com.example.testmodularizationapp.di

import com.example.testmodularizationapp.data.repository.auth.FirebaseAuthenticationImpl
import com.example.testmodularizationapp.domain.repository.auth.FirebaseAuthentication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindsFirebaseAuthenticationImpl(
        firebaseAuthenticationImpl: FirebaseAuthenticationImpl
    ): FirebaseAuthentication
}