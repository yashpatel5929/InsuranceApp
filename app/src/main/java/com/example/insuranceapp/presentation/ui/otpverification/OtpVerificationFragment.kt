package com.example.insuranceapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.insuranceapp.R
import com.example.insuranceapp.databinding.FragmentLoginBinding
import com.example.insuranceapp.databinding.FragmentOtpVerificationFragmentBinding
import com.example.insuranceapp.presentation.ui.login.VMLoginFragment
import com.example.insuranceapp.presentation.ui.otpverification.VMOtpVerification
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OtpVerificationFragment : Fragment() {

    lateinit var binding : FragmentOtpVerificationFragmentBinding
    private val viewModel : VMOtpVerification by viewModels()
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_otp_verification_fragment, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.otpObserver.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(R.id.registerAuthFragment)
            }
        }

        return view
    }


}