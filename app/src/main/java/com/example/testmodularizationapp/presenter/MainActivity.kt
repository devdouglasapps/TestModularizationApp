package com.example.testmodularizationapp.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.testmodularizationapp.R
import com.example.testmodularizationapp.databinding.ActivityMainBinding
import com.example.testmodularizationapp.databinding.FragmentRegisterBinding
import com.example.testmodularizationapp.presenter.auth.forgot.ForgotFragment
import com.example.testmodularizationapp.presenter.auth.login.LoginFragment
import com.example.testmodularizationapp.presenter.auth.register.RegisterFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginFragment = LoginFragment()

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.container, ForgotFragment())
        transaction.commit()

    }
}