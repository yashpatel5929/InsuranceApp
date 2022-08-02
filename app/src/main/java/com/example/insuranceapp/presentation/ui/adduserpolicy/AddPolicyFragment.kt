package com.example.insuranceapp.presentation.ui.adduserpolicy

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.insuranceapp.R
import com.example.insuranceapp.data.constants.Constants
import com.example.insuranceapp.databinding.FragmentAddPolicyBinding
import com.example.insuranceapp.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AddPolicyFragment : Fragment() {

    lateinit var binding: FragmentAddPolicyBinding
    private val viewModel: VMAddUserPolicyFragment by viewModels()
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_policy, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        subScribeUI()
        return view
    }

    private fun subScribeUI() = with(viewModel) {
        if (Constants.ADDORUPDATEPOLICY == 1) {
            binding.btnUpdateDetail.visibility = View.GONE
            binding.btnAddPolicy.visibility = View.VISIBLE
        } else {
            binding.btnUpdateDetail.visibility = View.VISIBLE
            binding.btnAddPolicy.visibility = View.GONE
        }
        binding.startDate.setOnClickListener {
            openDateOfBirthCalender(1)
        }
        binding.endDate.setOnClickListener {
            openDateOfBirthCalender(2)
        }
    }

    private fun openDateOfBirthCalender(id: Int) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd =
            activity?.let { fragmentActivity ->
                DatePickerDialog(
                    fragmentActivity,
                    R.style.DialogTheme,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                        val monthValue: Int = monthOfYear + 1
                        val date = "$dayOfMonth/$monthValue/$year"
                        date.also {
                            if (id == 1) {
                                binding.startDate.setText(it)
                            } else {
                                binding.endDate.setText(it)
                            }
                        }
                    },
                    year,
                    month,
                    day
                )
            }
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, 0)
        dpd?.datePicker?.maxDate = calendar.timeInMillis
        //dpd?.getButton(DatePickerDialog.BUTTON_NEGATIVE)?.setTextColor(Color.BLACK)
        dpd?.show()
    }


}