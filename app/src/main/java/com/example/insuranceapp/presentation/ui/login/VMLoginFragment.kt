package com.example.insuranceapp.presentation.ui.login

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.insuranceapp.data.storage.pref.AppPreferences
import com.example.insuranceapp.domain.NetworkResult
import com.example.insuranceapp.domain.usecases.loginauth.*
import com.example.insuranceapp.presentation.extensions.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMLoginFragment @Inject constructor(
private val appPreference : AppPreferences,
private val loginAuthUseCase : LoginAuthUseCase,
private val checkUserExistUseCase: CheckUserExistUseCase
):ViewModel() {

    val phoneNumber = MutableLiveData<String>()
    private val _loginResponse : MutableLiveData<NetworkResult<LoginResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<LoginResponse>> = _loginResponse

    private val _userExistResponse : MutableLiveData<NetworkResult<CheckUserExistResponse>> = MutableLiveData()
    val userExistResponse:LiveData<NetworkResult<CheckUserExistResponse>> = _userExistResponse

    fun setUserPhoneNumber(phoneNumber : String){
        appPreference.setUserPhoneNumber(phoneNumber)
    }

    fun fetchLoginData() = viewModelScope.launch {
        loginAuthUseCase.getLoginAuth(LoginAuth("patelyash9930@gmail.com","Yashp@6765")).collect{
            _loginResponse.value = it
        }
    }

    fun checkUserExistOrNot(phoneNumber : String) = viewModelScope.launch {
        checkUserExistUseCase.checkUserExist(CheckUserExist(phoneNumber)).collect{
            _userExistResponse.value = it
        }
    }

}