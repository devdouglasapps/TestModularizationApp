package com.example.testmodularizationapp.domain.usecase.auth

import com.example.testmodularizationapp.domain.repository.auth.FirebaseAuthentication
import javax.inject.Inject

class ForgotUseCase @Inject constructor(
    private val firebaseAuthentication: FirebaseAuthentication
){
    suspend operator fun invoke(email:String){
        firebaseAuthentication.forgot(email)
    }
}