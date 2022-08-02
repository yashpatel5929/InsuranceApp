package com.example.insuranceapp.domain.repository

import com.example.insuranceapp.domain.usecases.loginauth.CheckUserExist
import com.example.insuranceapp.domain.usecases.loginauth.CheckUserExistResponse
import com.example.insuranceapp.domain.usecases.loginauth.LoginAuth
import com.example.insuranceapp.domain.usecases.loginauth.LoginResponse
import com.example.insuranceapp.domain.usecases.loginauth.registration.RegistrationParam
import retrofit2.Response

interface LoginAuthRepository {
    suspend fun getLoginAuthData(params :LoginAuth): Response<LoginResponse>
    suspend fun checkUserExist(params: CheckUserExist) : Response<CheckUserExistResponse>
    suspend fun userRegistration(params:RegistrationParam):Response<Void>
}