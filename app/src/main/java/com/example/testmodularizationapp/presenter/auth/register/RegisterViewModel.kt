package com.example.testmodularizationapp.presenter.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.testmodularizationapp.domain.usecase.auth.RegisterUseCase
import com.example.testmodularizationapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel(){

    fun register(email: String, password: String) = liveData(Dispatchers.IO) {
        try {

            emit(StateView.Loading())
            registerUseCase.invoke(email,password)
            emit(StateView.Success(Unit))

        }catch (exception: Exception){

            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))

        }
    }
}