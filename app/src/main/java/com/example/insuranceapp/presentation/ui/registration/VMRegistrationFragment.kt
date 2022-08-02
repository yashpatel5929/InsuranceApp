package com.example.insuranceapp.presentation.ui.registration

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.insuranceapp.data.storage.pref.AppPreferences
import com.example.insuranceapp.domain.NetworkResult
import com.example.insuranceapp.domain.usecases.loginauth.CheckUserExist
import com.example.insuranceapp.domain.usecases.loginauth.LoginResponse
import com.example.insuranceapp.domain.usecases.loginauth.registration.RegistrationParam
import com.example.insuranceapp.domain.usecases.loginauth.registration.RegistrationUseCases
import com.example.insuranceapp.presentation.extensions.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMRegistrationFragment @Inject constructor(
    private val appPreference : AppPreferences,
    private val registrationUseCases:RegistrationUseCases
) :ViewModel(){


    val userPassword = MutableLiveData<String>()
    val validateFieldsObserver = MutableLiveData<Boolean>()
    val userName = MutableLiveData<String>()
    val userReTypePassword = MutableLiveData<String>()
    val isPasswordNotNull = MutableLiveData<Boolean>().apply { postValue(false) }
    val isRePasswordNotNull = MutableLiveData<Boolean>().apply { postValue(false) }
    val userPhoneNumber = MutableLiveData<String>()

    private val _registrationResponse : MutableLiveData<NetworkResult<Void>> = MutableLiveData()
    val response: LiveData<NetworkResult<Void>> = _registrationResponse

    fun validateFields(context: Context): Boolean {
        if (userName.value.isNullOrEmpty()){
            Toast.makeText(context, "Please enter Name", Toast.LENGTH_SHORT).show()
            return false
        }
        if (userPassword.value.isNullOrEmpty()) {
            Toast.makeText(context, "Please enter User-Password", Toast.LENGTH_SHORT).show()
            return false
        }

        if (userReTypePassword.value.isNullOrEmpty() || userPassword.value.toString().trim() != userReTypePassword.value.toString().trim()){
            Toast.makeText(context, "Password doesn't match", Toast.LENGTH_SHORT).show()
            return false
        }
        validateFieldsObserver.postValue(true)
        return true
    }

    fun getPhoneNumber(){
        userPhoneNumber.postValue(appPreference.getUserPhoneNumber())
    }

    fun userRegistration(params : RegistrationParam) = viewModelScope.launch {
        registrationUseCases.registration(params).collect{
            _registrationResponse.value = it
        }
    }




}