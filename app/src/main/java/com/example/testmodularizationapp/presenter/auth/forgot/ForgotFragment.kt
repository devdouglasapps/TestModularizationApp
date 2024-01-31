package com.example.testmodularizationapp.presenter.auth.forgot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.hellodev.netflix.util.FirebaseHelper
import com.bumptech.glide.Glide
import com.example.testmodularizationapp.R
import com.example.testmodularizationapp.databinding.FragmentForgotBinding
import com.example.testmodularizationapp.util.StateView
import com.example.testmodularizationapp.util.hideKeyboard
import com.example.testmodularizationapp.util.isEmailValid
import com.example.testmodularizationapp.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

fun Fragment.initToolbar(toolbar: androidx.appcompat.widget.Toolbar, showIconNavigation: Boolean = true) {
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title = ""

    if(showIconNavigation){
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    toolbar.setNavigationOnClickListener{
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}
@AndroidEntryPoint
class ForgotFragment : Fragment() {
    private val viewModel: ForgotViewModel by viewModels()

    private var _binding: FragmentForgotBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar = binding.toolbar)
        initListeners()
    }

    private fun initListeners() {
        binding.btnForgot.setOnClickListener { validateData() }

        Glide
            .with(requireContext())
            .load(R.drawable.loading)
            .into(binding.progressLoading)
    }

private fun validateData() {
    val email = binding.editEmail.text.toString()
    if(email.isEmailValid()) {
        hideKeyboard()
        forgot(email)
    }else {
       showSnackBar(message = R.string.text_email_empty_forgot_fragment)
    }
}

    private fun forgot(email: String) {
        viewModel.forgot(email).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progressLoading.isVisible = true
                }
                is StateView.Success -> {
                   showSnackBar(message = R.string.text_send_email_success_forgot_fragment)
                }
                is StateView.Error -> {
                    binding.progressLoading.isVisible = false
                    showSnackBar(
                        message = FirebaseHelper.validError(error= stateView.message ?: "")
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}