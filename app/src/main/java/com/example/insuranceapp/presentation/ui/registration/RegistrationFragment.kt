package com.example.insuranceapp.presentation.ui.registration

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.insuranceapp.R
import com.example.insuranceapp.databinding.FragmentOtpVerificationFragmentBinding
import com.example.insuranceapp.databinding.FragmentRegistrationBinding
import com.example.insuranceapp.databinding.FragmentRegistrationBindingImpl
import com.example.insuranceapp.domain.NetworkResult
import com.example.insuranceapp.domain.usecases.loginauth.registration.RegistrationParam
import com.example.insuranceapp.presentation.extensions.isNotValidPassword
import com.example.insuranceapp.presentation.ui.MainActivity
import com.example.insuranceapp.presentation.ui.otpverification.VMOtpVerification
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_registration.view.*

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    lateinit var binding : FragmentRegistrationBinding
    private val viewModel : VMRegistrationFragment by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).showToolBarWithBackButton("")
        (activity as MainActivity).hideBottomNav()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_registration, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        SubScribeUI()

        return view
    }

    private fun SubScribeUI() = with(viewModel) {
        getPhoneNumber()
        validateFieldsObserver.observe(viewLifecycleOwner){
            userRegistration(RegistrationParam(userPassword.value.toString(),userName.value.toString(),userPassword.value.toString()))
            response.observe(viewLifecycleOwner){
                when(it){
                    is NetworkResult.Success -> {
                        findNavController().navigate(R.id.loginAuthFragment)
                    }
                    is NetworkResult.Error -> {
                        Log.d(ContentValues.TAG, "subScribeUI:fail ${it.message}")
                    }
                    is NetworkResult.Loading -> {

                    }
                }
            }

        }

        userReTypePassword.observe(viewLifecycleOwner) {
            when {
                it.isNullOrEmpty() -> {
                    binding.reTypePassword.error = getString(R.string.requiredFiled)
                    isPasswordNotNull.postValue(false)
                }
                it.isNotValidPassword() -> {
                    binding.reTypePassword.error = getString(R.string.passwordStructure)
                    isPasswordNotNull.postValue(false)
                }
                else -> {
                    binding.reTypePassword.error = null
                    isPasswordNotNull.postValue(true)
                }
            }
        }
        userPassword.observe(viewLifecycleOwner) {
            when {
                it.isNullOrEmpty() -> {
                    binding.userPassword.error = getString(R.string.requiredFiled)
                    isPasswordNotNull.postValue(false)
                }
                it.isNotValidPassword() -> {
                    binding.userPassword.error = getString(R.string.passwordStructure)
                    isPasswordNotNull.postValue(false)
                }
                else -> {
                    binding.userPassword.error = null
                    isPasswordNotNull.postValue(true)
                }
            }
        }

        userName.observe(viewLifecycleOwner){
            when{
                it.isNullOrEmpty() -> {
                    binding.userNameLayout.error = getString(R.string.requiredFiled)
                    isPasswordNotNull.postValue(false)
                }
                else -> {
                    binding.userNameLayout.error = null
                    isPasswordNotNull.postValue(true)
                }
            }
        }


    }


}