package com.example.insuranceapp.presentation.ui.userpolicies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.insuranceapp.R
import com.example.insuranceapp.data.constants.Constants
import com.example.insuranceapp.databinding.FragmentUserPoliciesBinding
import com.example.insuranceapp.presentation.ui.MainActivity
import com.example.insuranceapp.presentation.ui.userAccess.UserAccessDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserPolicies : Fragment(),UserPoliciesAdapter.OnClickCardView{

    lateinit var binding : FragmentUserPoliciesBinding
    private val viewModel : VMUserPolicies by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).hideToolBar()
        (activity as MainActivity).showBottomNav()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_policies,container,false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        subScribeUI()
        return view
    }

    private fun subScribeUI() = with(viewModel){
        binding.rvUserPolicies.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvUserPolicies.adapter = UserPoliciesAdapter(this@UserPolicies)

        binding.floatingButton.setOnClickListener {
            Constants.ADDORUPDATEPOLICY = 1
            findNavController().navigate(R.id.addPolicyFragment)
        }
    }

    override fun onClickOpenEdit(position: Int) {
        Constants.ADDORUPDATEPOLICY = 2
        findNavController().navigate(R.id.addPolicyFragment)
    }

    override fun checkUserAccess() {
        val userAccessDialog = UserAccessDialogFragment.newInstance()
        userAccessDialog.show(
            childFragmentManager
        )
    }


}