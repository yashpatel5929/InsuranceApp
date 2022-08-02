package com.example.insuranceapp.data.impls

import com.example.insuranceapp.data.remote.api.LoginAPi
import com.example.insuranceapp.domain.repository.LoginAuthRepository
import com.example.insuranceapp.domain.usecases.loginauth.CheckUserExist
import com.example.insuranceapp.domain.usecases.loginauth.CheckUserExistResponse
import com.example.insuranceapp.domain.usecases.loginauth.LoginAuth
import com.example.insuranceapp.domain.usecases.loginauth.LoginResponse
import com.example.insuranceapp.domain.usecases.loginauth.registration.RegistrationParam
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginAuthRepositoryImpl @Inject constructor(
    private val loginAuthApi : LoginAPi
) : LoginAuthRepository {
    override suspend fun getLoginAuthData(params : LoginAuth): Response<LoginResponse> {
        return loginAuthApi.getLoginResponse(params.email,params.password)
    }

    override suspend fun checkUserExist(params: CheckUserExist): Response<CheckUserExistResponse> {
        return loginAuthApi.checkUserExist(params.phone)
    }

    override suspend fun userRegistration(params: RegistrationParam): Response<Void> {
        return loginAuthApi.userRegistration(params.phone,params.fullname,params.password)
    }


}