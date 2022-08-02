package com.example.insuranceapp.domain.usecases.loginauth.registration

import com.example.insuranceapp.domain.BaseApiResponse
import com.example.insuranceapp.domain.NetworkResult
import com.example.insuranceapp.domain.repository.LoginAuthRepository
import com.example.insuranceapp.domain.usecases.loginauth.CheckUserExist
import com.example.insuranceapp.domain.usecases.loginauth.CheckUserExistResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

open class RegistrationUseCases @Inject constructor(
    private var loginAuthApi :LoginAuthRepository
):BaseApiResponse(){
    suspend fun registration(params: RegistrationParam): Flow<NetworkResult<Void>> {
        return flow<NetworkResult<Void>> {
            emit(safeApiCall { loginAuthApi.userRegistration(params) })
        }.flowOn(Dispatchers.IO)
    }
}