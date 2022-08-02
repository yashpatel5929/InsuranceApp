package com.example.insuranceapp.presentation.ui.otpverification

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class VMOtpVerification @Inject constructor(
):ViewModel(){
    val otpVerification = MutableLiveData<String>()
    val otpObserver = MutableLiveData<Boolean>()
    fun otpVerifyOrNot(context:Context){
        if(otpVerification.value!= null && otpVerification.value!!.length == 4){
            otpObserver.postValue(true)
        }else{
            otpObserver.postValue(false)
            Toast.makeText(context, "Please enter correct otp", Toast.LENGTH_SHORT).show()
        }
    }
}