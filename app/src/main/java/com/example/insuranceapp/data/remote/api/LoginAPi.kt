package com.example.insuranceapp.data.remote.api

import com.example.insuranceapp.domain.usecases.loginauth.CheckUserExistResponse
import com.example.insuranceapp.domain.usecases.loginauth.LoginAuth
import com.example.insuranceapp.domain.usecases.loginauth.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface LoginAPi {

    @FormUrlEncoded
    @POST("login")
    suspend fun getLoginResponse(
        @Field("email") email: String,
        @Field("password") password:String
    ): Response<LoginResponse>

    @GET("user")
    suspend fun checkUserExist(
        @Query("phone") phone: String
    ):Response<CheckUserExistResponse>

    @FormUrlEncoded
    @POST("user")
    suspend fun userRegistration(
        @Field("phone") phone: String,
        @Field("fullname") fullname: String,
        @Field("password") password : String
    ):Response<Void>
}