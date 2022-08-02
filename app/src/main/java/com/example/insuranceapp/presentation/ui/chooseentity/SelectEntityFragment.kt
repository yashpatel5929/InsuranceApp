package com.example.insuranceapp.presentation.ui.chooseentity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.insuranceapp.R
import com.example.insuranceapp.data.constants.Constants
import com.example.insuranceapp.databinding.FragmentLoginBinding
import com.example.insuranceapp.databinding.FragmentSelectEntityBinding
import com.example.insuranceapp.presentation.ui.MainActivity
import com.example.insuranceapp.presentation.ui.login.VMLoginFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_select_entity.*

@AndroidEntryPoint
class SelectEntityFragment : Fragment() {
    lateinit var binding: FragmentSelectEntityBinding
    private val viewModel: VMSelectEntityFragment by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).showToolBarWithBackButton("Choose Entity")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_select_entity, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        subScribeUI()
        return view
    }

    private fun subScribeUI() = with(viewModel) {
        createEntity.observe(viewLifecycleOwner) {
            txtDescription.visibility = View.VISIBLE
            btnJoinEntity.text = getString(R.string.create_entity)
            binding.createEntity.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
            binding.joinEntity.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
            binding.createEntity.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.rounded_corner_with_lightgrey_color))
            binding.joinEntity.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.rounded_corner))
        }
        joinEntity.observe(viewLifecycleOwner) {
            txtDescription.visibility = View.GONE
            btnJoinEntity.text = getString(R.string.join_entity)
            binding.joinEntity.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
            binding.createEntity.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
            binding.joinEntity.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.rounded_corner_with_lightgrey_color))
            binding.createEntity.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.rounded_corner))
        }

        binding.btnJoinEntity.setOnClickListener {
            if(Constants.ENTITYCLASS == 1){

            }else{

            }
        }
    }
}

