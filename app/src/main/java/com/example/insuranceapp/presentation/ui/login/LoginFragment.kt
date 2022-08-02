package com.example.insuranceapp.presentation.ui.login

import android.content.ContentValues.TAG
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
import com.example.insuranceapp.databinding.FragmentLoginBinding
import com.example.insuranceapp.domain.NetworkResult
import com.example.insuranceapp.presentation.extensions.isNotValidPassword
import com.example.insuranceapp.presentation.extensions.isValidEmail
import com.example.insuranceapp.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

@AndroidEntryPoint
class LoginFragment : Fragment() {
    //lateinit var viewModel: VMLoginFragment
    lateinit var binding : FragmentLoginBinding
    private val viewModel : VMLoginFragment by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel = ViewModelProviders.of(requireActivity() as MainActivity)[VMLoginFragment::class.java]
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).hideToolBar()
        (activity as MainActivity).hideBottomNav()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subScribeUI()
    }

    private fun subScribeUI() = with(viewModel){
       // fetchLoginData()
//        response.observe(viewLifecycleOwner){
//            when(it){
//                is NetworkResult.Success -> {
//                    Log.d("message", "subScribeUI: success${it.data?.data}")
//                }
//                is NetworkResult.Error -> {
//                    Log.d(TAG, "subScribeUI:fail ${it.message}")
//                }
//                is NetworkResult.Loading -> {
//
//                }
//            }
//        }

        phoneNumber.observe(viewLifecycleOwner){
            when {
                it.length < 10 -> {
                    PhoneNumberLayout.error = getString(R.string.phoneNumberError)
                    binding.btnLogin.visibility = View.GONE
                    binding.btnRegister.visibility = View.GONE
                }
                else -> {
                    PhoneNumberLayout.error = null
                    //checkUserExistOrNot(phoneNumber.value.toString())
                    if(it == "9930566724"){
                       binding.btnRegister.visibility = View.VISIBLE
                        binding.btnLogin.visibility = View.GONE
                    }else{
                        binding.btnRegister.visibility = View.GONE
                        binding.btnLogin.visibility = View.VISIBLE
                    }
                }
            }
        }

        userExistResponse.observe(viewLifecycleOwner){
            when(it){
                is NetworkResult.Success -> {
                    if(it.data!!.exists){
                        Toast.makeText(requireContext(), "User Exist", Toast.LENGTH_SHORT).show()
                        binding.btnRegister.visibility = View.GONE
                        binding.btnLogin.visibility = View.VISIBLE
                    }else{
                        binding.btnRegister.visibility = View.VISIBLE
                        binding.btnLogin.visibility = View.GONE
                    }
                }
                is NetworkResult.Error -> {
                    Log.d(TAG, "subScribeUI:fail ${it.message}")
                }
                is NetworkResult.Loading -> {

                }
            }
        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.userPolicies)
        }

        binding.btnRegister.setOnClickListener {
            phoneNumber.value?.let { it1 -> setUserPhoneNumber(it1) }
            findNavController().navigate(R.id.otpVerificationFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return view
    }
}