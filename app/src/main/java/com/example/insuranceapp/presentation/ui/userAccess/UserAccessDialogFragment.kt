package com.example.insuranceapp.presentation.ui.userAccess

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.insuranceapp.R
import com.example.insuranceapp.presentation.ui.userpolicies.UserPoliciesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.check_user_access_to_policy.view.*


@AndroidEntryPoint
class UserAccessDialogFragment : DialogFragment() {

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.60).toInt()
        dialog?.window?.setLayout(width, height)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val v: View = inflater.inflate(R.layout.check_user_access_to_policy, container, false)
        v.rvUserAccess.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        v.rvUserAccess.adapter = UserAccessPolicyAdapter()

        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.bg_rounded_dialog)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_rounded_dialog)
    }


    companion object {
        private const val TAG = "UserAccessDialogFragment"
        fun newInstance() = UserAccessDialogFragment()
    }

    fun show(
        manager: FragmentManager
    ) {
        if (isAdded) return
        show(manager, TAG)
    }

}