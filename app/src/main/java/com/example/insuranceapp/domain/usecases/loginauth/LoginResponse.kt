package com.example.insuranceapp.domain.usecases.loginauth

data class LoginResponse(
    val data: Data,
    val message: String,
    val status: Int,
    val user_msg: String
)

data class Data(
    val access_token: String,
    val country_id: Any,
    val created: String,
    val dob: String,
    val email: String,
    val first_name: String,
    val gender: String,
    val id: Int,
    val is_active: Boolean,
    val last_name: String,
    val modified: String,
    val phone_no: Long,
    val profile_pic: String,
    val role_id: Int,
    val username: String
)