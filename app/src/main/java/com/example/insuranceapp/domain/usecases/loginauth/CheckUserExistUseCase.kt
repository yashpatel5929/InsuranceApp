package com.example.insuranceapp.domain.usecases.loginauth

import com.example.insuranceapp.domain.BaseApiResponse
import com.example.insuranceapp.domain.NetworkResult
import com.example.insuranceapp.domain.repository.LoginAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

open class CheckUserExistUseCase @Inject constructor(
    private val loginRepo : LoginAuthRepository
): BaseApiResponse() {
    suspend fun checkUserExist(params: CheckUserExist): Flow<NetworkResult<CheckUserExistResponse>> {
        return flow<NetworkResult<CheckUserExistResponse>> {
            emit(safeApiCall { loginRepo.checkUserExist(params) })
        }.flowOn(Dispatchers.IO)
    }

}